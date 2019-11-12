import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

public class CustomerDAO extends DAO {

    public CustomerDAO(Connection con1) {
        super(con1);
    }

    public void createTableCustomer() {
        String query = "create table customer (" +
                "idCustomer int(4) unsigned auto_increment not null, " +
                "pinCode varchar(25) not null, " +
                "email varchar(50) not null, " +
                "firstName varchar(25) not null, " +
                "lastName varchar(25) not null, " +
                "birthDay timestamp not null, " +
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

    public void createRecordCustomer(String pinCode, String email, String firstName, String lastName, LocalDateTime birthDay, String street, String apNr, int zip, String city, long creditCardNr, int cvc, int expiryDateYear, int expiryDateMonth){
        String query1 = "insert into customer (pinCode, email, firstName, lastName, birthDay, street, apNr, zip, city, creditCardNr, cvc, expiryDateYear, expiryDateMonth) values (";
        Timestamp birthDayTS = Timestamp.valueOf(birthDay);
        String query2 = "\"" + pinCode + "\", " +
                "\"" + email + "\", " +
                "\"" + firstName  + "\", " +
                "\"" + lastName  + "\", " +
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
        executeStatement(query, "Ein Datensatz customer der Tabelle customer zugefügt.");
    }

    public static List<Customer> select(Statement st) throws SQLException {
        List<Customer> customers = new LinkedList<Customer>();
        String query = "SELECT * FROM customer";
        ResultSet rs = st.executeQuery(query);
        while (rs.next())
        {
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





}
