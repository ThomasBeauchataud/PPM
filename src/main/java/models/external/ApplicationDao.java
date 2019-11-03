package models.external;

import org.json.simple.JSONObject;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import java.sql.*;
import java.util.List;

@Default
@ApplicationScoped
public class ApplicationDao extends NetworkDao implements ApplicationDaoInterface {

    @Override
    public void updateToken(String token, String appName) {
        List<JSONObject> logContent = logDaoFormatter.init();
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
        List<JSONObject> logContent = logDaoFormatter.init();
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

    @Override
    protected Object generateEntity(ResultSet resultSet) throws SQLException {
        return null;
    }

    private static final String update = "UPDATE application SET token = ? WHERE name = ?";
    private static final String select = "SELECT token FROM application WHERE name = ?";

}
