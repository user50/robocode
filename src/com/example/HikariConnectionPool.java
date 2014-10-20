package com.example;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class HikariConnectionPool implements ConnectionPool {

    private static final int CONNECTION_POOL_SIZE = 4;

    DataSource dataSource;

    public HikariConnectionPool(String url) {
        HikariConfig config = new HikariConfig();
        config.setConnectionTestQuery("SELECT version()");
        config.setConnectionTimeout(10000L);
        config.setMaximumPoolSize(CONNECTION_POOL_SIZE);
        config.setDataSourceClassName("com.mysql.jdbc.jdbc2.optional.MysqlDataSource");
        config.addDataSourceProperty("url", url);

        this.dataSource = new HikariDataSource(config);
    }

    @Override
    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    @Override
    public void releaseConnection(Connection connection) throws SQLException {
        connection.close();
    }
}
