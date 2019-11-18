package daos;

import main.DBConnector;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class DAO {
    protected DBConnector dbConnector;
    protected Connection connection;

    DAO() {
        dbConnector = DBConnector.getInstance();
    }

    void executeStatement(String query, String meldung) {
        try {
            Statement st = dbConnector.getConnection().createStatement();
            st.executeUpdate(query);
            st.close();
            System.out.println(meldung);
        } catch (SQLException sqle) {
            System.err.println("SQLException: " + sqle.getMessage());
            System.err.println("SQLState:     " + sqle.getSQLState());
            System.err.println("VendorError:  " + sqle.getErrorCode());
            sqle.printStackTrace();
        }
    }

    private int zaehlenDS(String query) {
        int anzahlDS = 0;
        try {
            Statement st = dbConnector.getConnection().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                anzahlDS = rs.getInt("anzahlDS");
            }
            st.close();
        } catch (SQLException sqle) {
            System.err.println("SQLException: " + sqle.getMessage());
            System.err.println("SQLState:     " + sqle.getSQLState());
            System.err.println("VendorError:  " + sqle.getErrorCode());
            sqle.printStackTrace();
        }
        return anzahlDS;
    }

    public void createQueryDeleteID(String table, String columnName, int zuLoeschendeId) {
        String query = "DELETE FROM " + table + " WHERE " + columnName + " = " + zuLoeschendeId;
        String meldung = "Ein Datensatz aus " + table + " mit der ID = " + zuLoeschendeId + " wurde gelöscht.";
        executeStatement(query, meldung);
    }

    public boolean checkIsXxxIdInTableXxx(String columnName, String tableName, int id) {
        boolean isIdInTable;
        String query = "select count(*) as anzahlDS from " + tableName + " where " + columnName + " = " + id;
        int anzahlDS = zaehlenDS(query);
        if (anzahlDS > 0) isIdInTable = true;
        else isIdInTable = false;
        return isIdInTable;
    }
}

