package daos;

import java.sql.Connection;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class LoanedDAO extends DAO {

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

    public void createRecordLoanedWithReturn(int idCustomer, int idBook, LocalDateTime loanedOn, LocalDateTime returnedOn) {
        Timestamp loanedOnTS = Timestamp.valueOf(loanedOn);
        Timestamp returnedOnTS = Timestamp.valueOf(returnedOn);
        String query1 = "insert into loaned (idCustomer, idBook, loanedOn, returnedOn, extraTime) values (";
        String query2 = idCustomer + ", " +
                idBook + ", " +
                "\"" + loanedOnTS + "\", " +
                "\"" + returnedOnTS + "\", " +
                false + ");";
        String query = query1 + query2;
        executeStatement(query, "Ein Datensatz loaned wurde der Tabelle loaned zugefügt. (Rückgabedatum eingetragen)");
    }


    public void createRecordLoanedWithExtraTime(int idCustomer, int idBook, LocalDateTime loanedOn, LocalDateTime returnedOn) {
        Timestamp loanedOnTS = Timestamp.valueOf(loanedOn);
        Timestamp returnedOnTS = Timestamp.valueOf(returnedOn);
        String query1 = "insert into loaned (idCustomer, idBook, loanedOn, returnedOn, extraTime) values (";
        String query2 = idCustomer + ", " +
                idBook + ", " +
                "\"" + loanedOnTS + "\", " +
                "\"" + returnedOnTS + "\", " +
                true + ");";
        String query = query1 + query2;
        executeStatement(query, "Ein Datensatz loaned wurde der Tabelle loaned zugefügt. (mit Verlängerungsvermerk)");
        createRecordLoanedWithoutReturn(idCustomer, idBook, returnedOn);
    }

    public void createRecordLoanedWithoutReturn(int idCustomer, int idBook, LocalDateTime loanedOn) {
        Timestamp loanedOnTS = Timestamp.valueOf(loanedOn);
        String query1 = "insert into loaned (idCustomer, idBook, loanedOn, extraTime) values (";
        String query2 = idCustomer + ", " +
                idBook + ", " +
                "\"" + loanedOnTS + "\", " +
                false + ");";
        String query = query1 + query2;
        executeStatement(query, "Ein Datensatz loaned wurde der Tabelle loaned zugefügt. (offene Rückgabe)");
    }
}
