import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

    public abstract class DAO {
        protected Connection connection;

        public DAO(Connection con1) {
            this.connection = con1;
        }

        public void executeStatement(String query, String meldung) {
            try {
                Statement st = this.getConnection().createStatement();
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

        public int zaehlenDS(String dateiname) {
            String query = "select count(*) as anzahlDS from " + dateiname;
            int anzahlDS = 0;
            try {
                Statement st = this.getConnection().createStatement();
                ResultSet rs = st.executeQuery(query);
                while (rs.next()) {
                    anzahlDS = rs.getInt("anzahlDS");
                }
                System.out.println("Die Datei " + dateiname + " hat " + anzahlDS + " Einträge.");
                st.close();
            } catch (SQLException sqle) {
                System.err.println("SQLException: " + sqle.getMessage());
                System.err.println("SQLState:     " + sqle.getSQLState());
                System.err.println("VendorError:  " + sqle.getErrorCode());
                sqle.printStackTrace();
            }
            return anzahlDS;
        }


        public Connection getConnection() {
            return connection;
        }

        public void setConnection(Connection connection) {
            this.connection = connection;
        }
    }

