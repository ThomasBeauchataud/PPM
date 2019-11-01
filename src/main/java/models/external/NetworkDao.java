package models.external;

import common.logger.formatter.LogDaoFormatterInterface;
import common.logger.formatter.LogType;
import org.json.simple.JSONObject;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.naming.Context;
import javax.naming.InitialContext;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

@Default
@ApplicationScoped
public class NetworkDao implements NetworkDaoInterface {

    @Inject
    protected LogDaoFormatterInterface logDaoFormatter;

    private Connection connection;

    @Override
    public void updateToken(String token, String appName) {
        List<JSONObject> logContent = logDaoFormatter.init(LogType.DAO);
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(update);
            preparedStatement.setString(1, token);
            preparedStatement.setString(2, appName);
            logDaoFormatter.addRequest(logContent, preparedStatement.toString());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            logDaoFormatter.addException(logContent, e);
        }
        logDaoFormatter.close(logContent);
    }

    @Override
    public String getToken(String appName) {
        List<JSONObject> logContent = logDaoFormatter.init(LogType.DAO);
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(select);
            preparedStatement.setString(1, appName);
            logDaoFormatter.addRequest(logContent, preparedStatement.toString());
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            String result = resultSet.getString("token");
            logDaoFormatter.addResponse(logContent, result);
            return result;
        } catch (Exception e) {
            logDaoFormatter.addException(logContent, e);
            logDaoFormatter.close(logContent);
            return null;
        }
    }

    private Connection getConnection() {
        if(connection != null) {
            return connection;
        }
        try {
            Class.forName( "com.mysql.cj.jdbc.Driver" );
            Context env = (Context)new InitialContext().lookup("java:comp/env");
            String db_url = "jdbc:mysql:" + env.lookup("nk-db-url") + "?autoReconnect=true&useSSL=false";
            String db_login = (String)env.lookup("nk-db-user");
            String db_password = (String)env.lookup("nk-db-password");
            this.connection = DriverManager.getConnection(db_url, db_login, db_password);
            return this.connection;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return this.connection;
        }
    }

    private static final String update = "UPDATE application SET token = ? WHERE name = ?";
    private static final String select = "SELECT token FROM application WHERE name = ?";

}
