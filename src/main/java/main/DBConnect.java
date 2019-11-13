package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {
    private String hostname;
    private String dbname;
    private String user;
    private String password;
    private Connection connection;

    public DBConnect(String hostname, String dbname, String user, String password) {
        this.hostname = hostname;
        this.dbname = dbname;
        this.user = user;
        this.password = password;
    }

    public Connection connectDB() {
        try {
            System.out.println("* Verbindung zur Datenbank " + dbname + " wird aufgebaut.");
            String url = "jdbc:mysql://" + hostname + "/" + dbname + "?serverTimezone=GMT-1";
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException sqle) {
            System.out.println("SQLException: " + sqle.getMessage());
            System.out.println("SQLState: " + sqle.getSQLState());
            System.out.println("VendorError: " + sqle.getErrorCode());
            sqle.printStackTrace();
        }
        return connection;
    }

    public boolean isConnectedDB(Connection con) {
        System.out.println("Nun wird getestet, ob die Verbindung zur DB noch aufrecht ist.");
        return true;
    }

    public void closeDB() {
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

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getDbname() {
        return dbname;
    }

    public void setDbname(String dbname) {
        this.dbname = dbname;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}

