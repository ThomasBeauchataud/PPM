package models.common;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.naming.Context;
import javax.naming.InitialContext;
import java.sql.Connection;
import java.sql.DriverManager;

@ApplicationScoped
@Default
public class DatabaseManager implements DatabaseManagerInterface {

    private Connection connection;

    @Override
    public Connection getConnection() {
        if(connection != null) {
            return connection;
        }
        try {
            Class.forName( "com.mysql.cj.jdbc.Driver" );
            Context env = (Context)new InitialContext().lookup("java:comp/env");
            String db_url = "jdbc:mysql:" + env.lookup("db-url") + "?autoReconnect=true&useSSL=false";
            String db_login = (String)env.lookup("db-user");
            String db_password = (String)env.lookup("db-password");
            this.connection = DriverManager.getConnection(db_url, db_login, db_password);
            return this.connection;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

}
