package com.example;

import fuck.inductivitylearning.Action;
import fuck.inductivitylearning.Environment;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by user50 on 26.09.2014.
 */
public class SaveState implements Update {

    Action action;
    Environment environment;
    Environment previousEnvironment;

    public SaveState(Action action, Environment environment, Environment previousEnvironment) {
        this.action = action;
        this.environment = environment;
        this.previousEnvironment = previousEnvironment;
    }

    @Override
    public String getRawSql() {
        return "INSERT INTO `Stats` (`ahead`, `rotate`, `hearing`, `velocity`, `x`, `y`, `previousHearing`, `previousVelocity`, `previousX`, `previousY`) " +
                "VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    }

    @Override
    public void prepare(PreparedStatement statement) throws SQLException {
        int i = 1;
        statement.setDouble(i++, action.getAhead());
        statement.setDouble(i++, action.getTurn());
        statement.setDouble(i++, environment.getMyHearing());
        statement.setDouble(i++, environment.getMyVelocity());
        statement.setDouble(i++, environment.getX());
        statement.setDouble(i++, environment.getY());
        statement.setDouble(i++, previousEnvironment.getMyHearing());
        statement.setDouble(i++, previousEnvironment.getMyVelocity());
        statement.setDouble(i++, previousEnvironment.getX());
        statement.setDouble(i  , previousEnvironment.getY());
    }
}
