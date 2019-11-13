package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {
    private static DBConnector instance;
    private Connection connection;
    private DBConnector(){
    }

    public static DBConnector getInstance(){
        if (instance == null) {
            instance = new DBConnector(
            );
        }
        return instance;
    }

    public void connect(String hostname, String dbname, String user, String password) {
        try {
            System.out.println("* Verbindung zur Datenbank " + dbname + " wird aufgebaut.");
            String url = "jdbc:mysql://" + hostname + "/" + dbname + "?serverTimezone=GMT-1&zeroDateTimeBehavior=CONVERT_TO_NULL";
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException sqle) {
            System.out.println("SQLException: " + sqle.getMessage());
            System.out.println("SQLState: " + sqle.getSQLState());
            System.out.println("VendorError: " + sqle.getErrorCode());
            sqle.printStackTrace();
        }
    }

    public void close() {
        try {
            System.out.println("* Datenbank-Verbindung wird beendet.");
            connection.close();
        } catch (SQLException sqle) {
            System.out.println("SQLException: " + sqle.getMessage());
            System.out.println("SQLState: " + sqle.getSQLState());
            System.out.println("VendorError: " + sqle.getErrorCode());
            sqle.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
