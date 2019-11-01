package models.common;

import java.sql.Connection;

public interface DatabaseManagerInterface {

    Connection getConnection();

}
