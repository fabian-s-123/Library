import java.sql.Connection;

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
                "creditCardNr int(16), " +
                "cvc int(3), " +
                "expiryDateYear int(4), " +
                "expiryDateMonth int(2), " +
                "created_at timestamp default current_timestamp, " +
                "updated_at timestamp default current_timestamp on update current_timestamp, " +
                "primary key (idCustomer)" +
                ");";
        executeStatement(query, "Die Tabelle customer wurde angelegt.");
    }
}
