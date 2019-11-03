package webservice;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public class ExecuteExternalPaymentTest extends AbstractWebserviceTest {

    private static Connection connection;

    public static void main(String[] args) {
        try {
            List<Payment> paymentList = generatePaymentList();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("app", "SR-TP2");
            jsonObject.put("token", getToken());
            JSONArray jsonArray = new JSONArray();
            for (Payment payment : paymentList) {
                JSONObject temp = new JSONObject();
                temp.put("email", payment.getUser().getEmail());
                temp.put("value", payment.getValue());
                jsonArray.add(temp);
            }
            jsonObject.put("transactions", jsonArray);
            String url = "http://localhost:18080/web_war_exploded/api/execute-external-payment";
            String request = jsonObject.toJSONString();
            System.out.println(sendPost(url, request));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static List<Payment> generatePaymentList() {
        List<Payment> paymentList = new ArrayList<>();
        User user1 = new User();
        user1.setEmail("email1");
        User user2 = new User();
        user2.setEmail("email2");
        paymentList.add(new Payment(user1, 10));
        paymentList.add(new Payment(user2, 20));
        return paymentList;
    }

    private static String getToken() {
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(select);
            preparedStatement.setString(1, "SR-TP2");
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getString("token");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static Connection getConnection() {
        if(connection != null) {
            return connection;
        }
        try {
            Class.forName( "com.mysql.cj.jdbc.Driver" );
            String db_url = "jdbc:mysql://localhost:3306/network?autoReconnect=true&useSSL=false";
            String db_login = "root";
            String db_password = "0000";
            connection = DriverManager.getConnection(db_url, db_login, db_password);
            return connection;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return connection;
        }
    }

    private static final String select = "SELECT token FROM application WHERE name = ?";

}
