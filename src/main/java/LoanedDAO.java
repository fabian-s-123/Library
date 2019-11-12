import java.sql.Connection;

public class LoanedDAO extends DAO{

    public LoanedDAO(Connection con1) {
        super(con1);
    }

    public void createTableLoaned() {
        String query = "create table loaned (" +
                "idLoaned int(5) unsigned auto_increment not null, " +
                "idCustomer int(4) not null, " +
                "idBook int(4) not null, " +
                "loanedOn timestamp not null default 0, " +
                "returnedOn timestamp, " +
                "extraTime boolean, " +
                "created_at timestamp default current_timestamp, " +
                "updated_at timestamp default current_timestamp on update current_timestamp, " +
                "primary key (idLoaned)" +
                ");";
        executeStatement(query, "Die Tabelle loaned wurde angelegt.");
    }
}
