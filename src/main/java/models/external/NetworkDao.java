package models.external;

import com.github.ffcfalcos.commondao.CommonDao;

import javax.naming.Context;
import javax.naming.InitialContext;
import java.sql.Connection;
import java.sql.DriverManager;

public abstract class NetworkDao extends CommonDao {

    private Connection connection;

    @Override
    protected Connection getConnection() {
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

}
