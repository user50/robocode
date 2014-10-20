package com.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {

    private ConnectionPool connectionPool;

    public DatabaseManager(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    public <T> List<T> executeQueryForList(Query<T> query) throws SQLException {

        long start = System.currentTimeMillis();
        Connection connection = connectionPool.getConnection();

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            String rawSql = query.getRawSql();
            preparedStatement = connection.prepareStatement(rawSql);
            query.prepare(preparedStatement);

            start = System.currentTimeMillis();
            resultSet = preparedStatement.executeQuery();

            List<T> list = new ArrayList<T>();

            while (resultSet.next()){
                list.add(query.fill(resultSet));
            }

            return list;
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }

            connectionPool.releaseConnection(connection);
        }
    }

    public <T> T executeQueryForOne(Query<T> query) throws SQLException {
        List<T> list = executeQueryForList(query);

        if (list.size() > 1)
            throw new RuntimeException("Ambiguity during executing query.");

        if (list.size() == 1 && list.get(0) == null)
            return null;

        return list.isEmpty()? null:list.get(0);
    }

    public int executeUpdate(Update update) throws SQLException {
        Connection connection = connectionPool.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            String rawSql = update.getRawSql();
            preparedStatement = connection.prepareStatement(rawSql);
            update.prepare(preparedStatement);

            return preparedStatement.executeUpdate();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }

            connectionPool.releaseConnection(connection);
        }
    }

    public void setConnectionPool(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }
}
