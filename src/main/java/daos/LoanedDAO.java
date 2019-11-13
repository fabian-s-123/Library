package daos;

import java.sql.*;
import entities.Loaned;
import entities.LoanedCustomerBook;
import sun.awt.image.ImageWatched;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;

public class LoanedDAO extends DAO {
    public LoanedDAO() {
    }

    public void createTableLoaned() {
        String query = "create table loaned (" +
                "idLoaned int(5) unsigned auto_increment not null, " +
                "idCustomer int(4) not null, " +
                "idBook int(4) not null, " +
                "loanedOn timestamp not null default 0, " +
                "returnedOn timestamp null, " +
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

    public LinkedList<Loaned> getListeoffeneRückgabenKurz() {
        LinkedList<Loaned> listLoaned = new LinkedList<>();
        String query = "select * from loaned";
        listLoaned = createLinkedListLoaned(query);
        return listLoaned;
    }

    public LinkedList<Loaned> createLinkedListLoaned(String query) {
        LinkedList<Loaned> listLoaned = new LinkedList<>();
        try {
            Statement st = dbConnector.getConnection().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                int idLoaned = rs.getInt(1);
                int idCustomer = rs.getInt(2);
                int idBook = rs.getInt(3);
                LocalDateTime loanedOn = rs.getTimestamp(4).toLocalDateTime();
                LocalDateTime returnedOn = null;
                Timestamp tsReturnedOn = rs.getTimestamp(5);
                if (tsReturnedOn != null) {
                    returnedOn = tsReturnedOn.toLocalDateTime();
                }
                boolean extraTime = rs.getBoolean(6);
                LocalDateTime created_at = rs.getTimestamp(7).toLocalDateTime();
                LocalDateTime updated_at = rs.getTimestamp(8).toLocalDateTime();
                Loaned temp = new Loaned(idLoaned, idCustomer, idBook, loanedOn, returnedOn, extraTime, created_at, updated_at);
                listLoaned.add(temp);
            }
            st.close();
        } catch (SQLException sqle) {
            System.out.println("SQLException: " + sqle.getMessage());
            System.out.println("SQLState: " + sqle.getSQLState());
            System.out.println("VendorError: " + sqle.getErrorCode());
            sqle.printStackTrace();
        }
        return listLoaned;
    }

    public LinkedList<LoanedCustomerBook> getListeLCB_sortCustomer() {
        LinkedList<LoanedCustomerBook> listLCB = new LinkedList<>();
        String query = "select * from (((loaned inner join customer on loaned.idCustomer=customer.idCustomer) inner join book on loaned.idBook = book.idBook) inner join author on book.idAuthor = author.idAuthor) order by loaned.idCustomer ASC";
        listLCB = createLinkedListLCB(query);
        return listLCB;
    }

    public LinkedList<LoanedCustomerBook> getListeLCB_sortBook() {
        LinkedList<LoanedCustomerBook> listLCB = new LinkedList<>();
        String query = "select * from (((loaned inner join customer on loaned.idCustomer=customer.idCustomer) inner join book on loaned.idBook = book.idBook) inner join author on book.idAuthor = author.idAuthor) order by loaned.idBook ASC";
        listLCB = createLinkedListLCB(query);
        return listLCB;
    }

    public LinkedList<LoanedCustomerBook> createLinkedListLCB(String query) {
        LinkedList<LoanedCustomerBook> listLCB = new LinkedList<>();
        try {
            Statement st = dbConnector.getConnection().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                int idLoaned = rs.getInt(1);
                int idCustomer = rs.getInt(2);
                int idBook = rs.getInt(3);
                LocalDateTime loanedOn = rs.getTimestamp(4).toLocalDateTime();
                LocalDateTime returnedOn = null;
                Timestamp tsReturnedOn = rs.getTimestamp(5);
                if (tsReturnedOn != null) {
                    returnedOn = tsReturnedOn.toLocalDateTime();
                }
                boolean extraTime = rs.getBoolean(6);
                LocalDateTime created_at = rs.getTimestamp(7).toLocalDateTime();
                LocalDateTime updated_at = rs.getTimestamp(8).toLocalDateTime();
                String customerFirstName = rs.getString(12);
                String customerLastName = rs.getString(13);
                String title = rs.getString(26);
                String authorFirstName = rs.getString(41);
                String authorLastName = rs.getString(42);
                LoanedCustomerBook temp = new LoanedCustomerBook(idLoaned, idCustomer, customerFirstName, customerLastName, idBook, title, authorFirstName, authorLastName, loanedOn, returnedOn, extraTime, created_at, updated_at);
                listLCB.add(temp);
            }
            st.close();
        } catch (SQLException sqle) {
            System.out.println("SQLException: " + sqle.getMessage());
            System.out.println("SQLState: " + sqle.getSQLState());
            System.out.println("VendorError: " + sqle.getErrorCode());
            sqle.printStackTrace();
        }
        return listLCB;
    }

    public static List<Integer> selectIdBooksLoaned(Statement st) throws SQLException {
        List<Integer> ids = new ArrayList<Integer>();
        String query = "SELECT idBook FROM loaned;";
        ResultSet rs = st.executeQuery(query);
        while (rs.next())
        {
            int idBook = rs.getInt("idBook");
            ids.add(idBook);
        }
        return ids;
    }

    public static List<Timestamp> selectBookReturned(Statement st, int idBook) throws SQLException {
        List<Timestamp> ids = new ArrayList<>();
        String query = "SELECT returnedOn FROM loaned WHERE idBook="+ idBook + " ORDER BY `idLoaned` DESC LIMIT 1;";
        ResultSet rs = st.executeQuery(query);
        while (rs.next()){
            Timestamp returnedOn = rs.getTimestamp("returnedOn");
            ids.add(returnedOn);
        }
        return ids;
    }





}
