package daos;

import entities.*;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CustomerDAO extends DAO {
    public CustomerDAO() {
    }

    public void createTableCustomer() {
        String query = "create table customer (" +
                "idCustomer int(4) unsigned auto_increment not null, " +
                "pinCode varchar(25) not null, " +
                "email varchar(50) not null, " +
                "firstName varchar(25) not null, " +
                "lastName varchar(25) not null, " +
                "birthDay timestamp not null default 0, " +
                "street varchar(50) not null, " +
                "apNr varchar(7) not null, " +
                "zip int(5) not null, " +
                "city varchar(20) not null, " +
                "creditCardNr bigint(16), " +
                "cvc int(3), " +
                "expiryDateYear int(4), " +
                "expiryDateMonth int(2), " +
                "created_at timestamp default current_timestamp, " +
                "updated_at timestamp default current_timestamp on update current_timestamp, " +
                "primary key (idCustomer)" +
                ");";
        executeStatement(query, "Die Tabelle customer wurde angelegt.");
    }

    public void createRecordCustomer(String pinCode, String email, String firstName, String lastName, LocalDateTime birthDay, String street, String apNr, int zip, String city, long creditCardNr, int cvc, int expiryDateYear, int expiryDateMonth) {
        String query1 = "insert into customer (pinCode, email, firstName, lastName, birthDay, street, apNr, zip, city, creditCardNr, cvc, expiryDateYear, expiryDateMonth) values (";
        Timestamp birthDayTS = Timestamp.valueOf(birthDay);
        String query2 = "\"" + pinCode + "\", " +
                "\"" + email + "\", " +
                "\"" + firstName + "\", " +
                "\"" + lastName + "\", " +
                "\"" + birthDayTS + "\", " +
                "\"" + street + "\", " +
                "\"" + apNr + "\", " +
                zip + ", " +
                "\"" + city + "\", " +
                creditCardNr + ", " +
                cvc + ", " +
                expiryDateYear + ", " +
                expiryDateMonth + ");";
        String query = query1 + query2;
        executeStatement(query, "Ein Datensatz der Tabelle customer zugefügt.");
    }

    public void updateRecordCustomer(String pinCode, String email, String firstName, String lastName, LocalDateTime birthDay, String street, String apNr, int zip, String city, long creditCardNr, int cvc, int expiryDateYear, int expiryDateMonth, int idCustomer) {
        Timestamp birthDayTS = Timestamp.valueOf(birthDay);
        String query1 = "update customer set ";
        String query2 = "pinCode = " + "\"" + pinCode + "\", " +
                "email = " + "\"" + email + "\", " +
                "firstName = " + "\"" + firstName + "\", " +
                "lastName = " + "\"" + lastName + "\", " +
                "birthDay = " + "\"" + birthDayTS + "\", " +
                "street = " + "\"" + street + "\", " +
                "apNr = " + "\"" + apNr + "\", " +
                "zip = " + zip + ", " +
                "city = " + "\"" + city + "\", " +
                "creditCardNr = " + creditCardNr + ", " +
                "cvc = " + cvc + ", " +
                "expiryDateYear = " + expiryDateYear + ", " +
                "expiryDateMonth = " + expiryDateMonth;
        String query3 = " where idCustomer = " + idCustomer + ";";
        String query = query1 + query2 + query3;
        executeStatement(query, "Ein Datensatz wurde in der Tabelle customer editiert/geupdated.");
    }

    public List<Customer> select(Statement st) throws SQLException {
        List<Customer> customers = new LinkedList<Customer>();
        String query = "SELECT * FROM customer";
        ResultSet rs = st.executeQuery(query);
        while (rs.next()) {
            int idCustomer = rs.getInt("idCustomer");
            String pinCode = rs.getString("pinCode");
            String email = rs.getString("email");
            String firstName = rs.getString("firstName");
            String lastName = rs.getString("lastName");
            Timestamp birthday = rs.getTimestamp("birthday");
            String street = rs.getString("street");
            String apNr = rs.getString("apNr");
            int zip = rs.getInt("zip");
            String city = rs.getString("city");
            int creditCardNr = rs.getInt("creditCardNr");
            int CVC = rs.getInt("CVC");
            int expiryDateYear = rs.getInt("expiryDateYear");
            int expiryDateMonth = rs.getInt("expiryDateMonth");

            Customer customer = new Customer(idCustomer, pinCode, email, firstName, lastName, birthday, street, apNr, zip, city, creditCardNr, CVC, expiryDateYear, expiryDateMonth);
            customers.add(customer);
        }
        return customers;
    }

    public List<Integer> selectIdCustomer(Statement st) throws SQLException {
        ArrayList<Integer> ids = new ArrayList<>();
        String query = "SELECT idCustomer FROM customer;";
        ResultSet rs = st.executeQuery(query);
        while (rs.next()) {
            int idCustomer = rs.getInt("idCustomer");
            ids.add(idCustomer);
        }
        return ids;
    }

    public String selectPinCode(Statement st, int idCustomer) throws SQLException {
        String query = "SELECT pinCode FROM customer WHERE idCustomer =" + idCustomer + ";";
        String pinCode = "";
        ResultSet rs = st.executeQuery(query);
        while (rs.next()) {
            pinCode = rs.getString("pinCode");
        }
        return pinCode;
    }

    public String selectFirstName(Statement st, int idCustomer) throws SQLException {
        String query = "SELECT firstName FROM customer WHERE idCustomer =" + idCustomer + ";";
        String firstName = "";
        ResultSet rs = st.executeQuery(query);
        while (rs.next()) {
            firstName = rs.getString("firstName");
        }
        return firstName;
    }

    public Timestamp selectBirthDay(Statement st, int idCustomer) throws SQLException {
        String query = "SELECT birthDay FROM customer WHERE idCustomer =" + idCustomer + ";";
        Timestamp birthDay = null;
        ResultSet rs = st.executeQuery(query);
        while (rs.next()) {
            birthDay = rs.getTimestamp("birthDay");
        }
        return birthDay;
    }

    public LinkedList<Customer> getListAllCustomers() {
        String query = "select * from customer order by customer.lastName ASC";
        LinkedList<Customer> listAllCustomers = createLinkedListAllCustomers(query);
        return listAllCustomers;
    }

    private LinkedList<Customer> createLinkedListAllCustomers(String query) {
        LinkedList<Customer> listAC = new LinkedList<>();
        try {
            Statement st = dbConnector.getConnection().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                int idCustomer = rs.getInt("idCustomer");
                String pinCode = rs.getString("pinCode");
                String email = rs.getString("email");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                Timestamp birthDay = rs.getTimestamp("birthDay");
                String street = rs.getString("street");
                String apNr = rs.getString("apNr");
                int zip = rs.getInt("zip");
                String city = rs.getString("city");
                long creditCardNr = rs.getLong("creditCardNr");
                int CVC = rs.getInt("cvc");
                int expiryDateYear = rs.getInt("expiryDateYear");
                int expiryDateMonth = rs.getInt("expiryDateMonth");
                LocalDateTime created_at = rs.getTimestamp("created_at").toLocalDateTime();
                LocalDateTime updated_at = rs.getTimestamp("updated_at").toLocalDateTime();
                Customer temp = new Customer(idCustomer, pinCode, email, firstName, lastName, birthDay, street, apNr, zip, city, creditCardNr, CVC, expiryDateYear, expiryDateMonth, created_at, updated_at);
                listAC.add(temp);
            }
            st.close();
        } catch (SQLException sqle) {
            System.out.println("SQLException: " + sqle.getMessage());
            System.out.println("SQLState: " + sqle.getSQLState());
            System.out.println("VendorError: " + sqle.getErrorCode());
            sqle.printStackTrace();
        }
        return listAC;
    }
}