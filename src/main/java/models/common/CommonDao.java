package models.common;

import common.logger.formatter.LogDaoFormatterInterface;
import common.logger.formatter.LogType;
import org.json.simple.JSONObject;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class CommonDao<T> implements CommonDaoInterface<T> {

    @Inject
    private DatabaseManagerInterface databaseManager;
    @Inject
    protected LogDaoFormatterInterface logDaoFormatter;

    protected Connection getConnection() {
        return this.databaseManager.getConnection();
    }

    protected abstract T generateEntity(ResultSet resultSet) throws SQLException;

    protected void insert(String query, Object[] parameters) {
        List<JSONObject> logContent = logDaoFormatter.init(LogType.DAO);
        try {
            PreparedStatement preparedStatement = this.generateStatement(query, parameters);
            logDaoFormatter.addRequest(logContent, preparedStatement.toString());
            preparedStatement.execute();
        } catch (Exception e) {
            logDaoFormatter.addException(logContent, e);
        }
        logDaoFormatter.close(logContent);
    }

    protected void update(String query, Object[] parameters) {
        List<JSONObject> logContent = logDaoFormatter.init(LogType.DAO);
        try {
            PreparedStatement preparedStatement = this.generateStatement(query, parameters);
            logDaoFormatter.addRequest(logContent, preparedStatement.toString());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            logDaoFormatter.addException(logContent, e);
        }
        logDaoFormatter.close(logContent);
    }

    protected T getById(String query, int id) {
        List<JSONObject> logContent = logDaoFormatter.init(LogType.DAO);
        try {
            PreparedStatement preparedStatement = this.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, id);
            logDaoFormatter.addRequest(logContent, preparedStatement.toString());
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            T result = generateEntity(resultSet);
            logDaoFormatter.addResponse(logContent, result);
            return result;
        } catch (Exception e) {
            logDaoFormatter.addException(logContent, e);
            logDaoFormatter.close(logContent);
            return null;
        }
    }

    protected T getOne(String query, Object[] parameters) {
        List<JSONObject> logContent = logDaoFormatter.init(LogType.DAO);
        try {
            PreparedStatement preparedStatement = this.generateStatement(query, parameters);
            logDaoFormatter.addRequest(logContent, preparedStatement.toString());
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            T result = generateEntity(resultSet);
            logDaoFormatter.addResponse(logContent, result);
            return result;
        } catch (Exception e) {
            logDaoFormatter.addException(logContent, e);
            logDaoFormatter.close(logContent);
            return null;
        }
    }

    protected List<T> getMultiple(String query, Object[] parameters) {
        List<JSONObject> logContent = logDaoFormatter.init(LogType.DAO);
        List<T> list = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = this.generateStatement(query, parameters);
            logDaoFormatter.addRequest(logContent, preparedStatement.toString());
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                list.add(generateEntity(resultSet));
            }
        } catch (Exception e) {
            logDaoFormatter.addException(logContent, e);
        }
        logDaoFormatter.addResponse(logContent, list);
        return list;
    }

    protected List<T> getAll(String query) {
        List<JSONObject> logContent = logDaoFormatter.init(LogType.DAO);
        List<T> list = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = this.getConnection().prepareStatement(query);
            logDaoFormatter.addRequest(logContent, preparedStatement.toString());
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                list.add(generateEntity(resultSet));
            }
        } catch (Exception e) {
            logDaoFormatter.addException(logContent, e);
        }
        logDaoFormatter.addResponse(logContent, list);
        return list;
    }

    protected void deleteById(String query, int id) {
        List<JSONObject> logContent = logDaoFormatter.init(LogType.DAO);
        try {
            PreparedStatement preparedStatement = this.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, id);
            logDaoFormatter.addRequest(logContent, preparedStatement.toString());
            preparedStatement.execute();
        } catch (Exception e) {
            logDaoFormatter.addException(logContent, e);
        }
        logDaoFormatter.close(logContent);
    }

    protected void delete(String query, Object[] parameters) {
        insert(query, parameters);
    }

    private PreparedStatement generateStatement(String query, Object[] parameters) throws SQLException {
        PreparedStatement preparedStatement = this.getConnection().prepareStatement(query);
        for (int i = 0; i < parameters.length; i++) {
            Object parameter = parameters[i];
            if (parameter.getClass() == Double.class) {
                preparedStatement.setDouble(i + 1, (double) parameter);
                continue;
            }
            if (parameter.getClass() == Integer.class) {
                preparedStatement.setInt(i + 1, (int) parameter);
                continue;
            }
            if (parameter.getClass() == String.class) {
                preparedStatement.setString(i + 1, (String) parameter);
                continue;
            }
            if (parameter.getClass() == Boolean.class) {
                preparedStatement.setBoolean(i + 1, (boolean) parameter);
                continue;
            }
            if (parameter.getClass() == Long.class) {
                preparedStatement.setLong(i + 1, (long) parameter);
            }
        }
        return preparedStatement;
    }

}
