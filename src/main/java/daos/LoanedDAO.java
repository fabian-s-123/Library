package daos;

import java.sql.*;

import entities.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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
        executeStatement(query, "Ein Datensatz wurde der Tabelle loaned zugefügt. (Rückgabedatum eingetragen)");
    }

    public void createRecordLoanedWithExtraTime(int idCustomer, int idBook, LocalDateTime loanedOn) {
        Timestamp loanedOnTS = Timestamp.valueOf(loanedOn);
        //Timestamp returnedOnTS = Timestamp.valueOf(returnedOn);
        String query1 = "insert into loaned (idCustomer, idBook, loanedOn, extraTime) values (";
        String query2 = idCustomer + ", " +
                idBook + ", " +
                "\"" + loanedOnTS + "\", " +
                //"\"" + returnedOnTS + "\", " +
                true + ");";
        String query = query1 + query2;
        executeStatement(query, "Ein Datensatz loaned wurde der Tabelle loaned zugefügt. (mit Verlängerungsvermerk)");
        //createRecordLoanedWithoutReturn(idCustomer, idBook, loanedOn);
    }

    public void createRecordLoanedWithoutReturn(int idCustomer, int idBook, LocalDateTime loanedOn) {
        Timestamp loanedOnTS = Timestamp.valueOf(loanedOn);
        String query1 = "insert into loaned (idCustomer, idBook, loanedOn, extraTime) values (";
        String query2 = idCustomer + ", " +
                idBook + ", " +
                "\"" + loanedOnTS + "\", " +
                false + ");";
        String query = query1 + query2;
        executeStatement(query, "Ein Datensatz wurde der Tabelle loaned zugefügt. (offene Rückgabe)");
    }

    public void createNewRecordLoaned(int idCustomer, int idBook, LocalDateTime loanedOn) {
        Timestamp loanedOnTS = Timestamp.valueOf(loanedOn);
        String query1 = "insert into loaned (idCustomer, idBook, loanedOn, extraTime) values (";
        String query2 = idCustomer + ", " +
                idBook + ", " +
                "\"" + loanedOnTS + "\", " +
                false + ");";
        String query = query1 + query2;
        executeStatement(query, "One moment please...\nHere is your book.\n");
    }

    public void returnBook(int idCustomer, int idBook, LocalDateTime loanedOn, LocalDateTime returnedOn) throws SQLException {
        Timestamp loanedOnTS = Timestamp.valueOf(loanedOn);
        Timestamp returnedOnTS = Timestamp.valueOf(returnedOn);
        String query = "update loaned set returnedOn='" + returnedOnTS + "' where idCustomer=" + idCustomer + " and idBook=" + idBook + " and loanedOn ='" + loanedOnTS + "';";
        executeStatement(query, "Thank you for returning.");
    }

    public LinkedList<Loaned> createLinkedListLoaned(String query) {
        LinkedList<Loaned> listLoaned = new LinkedList<>();
        try {
            Statement st = dbConnector.getConnection().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                int idLoaned = rs.getInt("idLoaned");
                int idCustomer = rs.getInt("idCustomer");
                int idBook = rs.getInt("idBook");
                LocalDateTime loanedOn = rs.getTimestamp("loanedOn").toLocalDateTime();
                LocalDateTime returnedOn = null;
                Timestamp tsReturnedOn = rs.getTimestamp("returnedOn");
                if (tsReturnedOn != null) {
                    returnedOn = tsReturnedOn.toLocalDateTime();
                }
                boolean extraTime = rs.getBoolean("extraTime");
                LocalDateTime created_at = rs.getTimestamp("created_at").toLocalDateTime();
                LocalDateTime updated_at = rs.getTimestamp("updated_at").toLocalDateTime();
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
        String query = "select * from (((loaned inner join customer on loaned.idCustomer=customer.idCustomer) inner join book on loaned.idBook = book.idBook) inner join author on book.idAuthor = author.idAuthor) order by customer.lastName, loaned.idBook";
        LinkedList<LoanedCustomerBook> listLCB = createLinkedListLCB(query);
        return listLCB;
    }

    public LinkedList<LoanedCustomerBook> getListeLCB_sortBook() {
        String query = "select * from (((loaned inner join customer on loaned.idCustomer=customer.idCustomer) inner join book on loaned.idBook = book.idBook) inner join author on book.idAuthor = author.idAuthor) order by loaned.idBook ASC";
        LinkedList<LoanedCustomerBook> listLCB = createLinkedListLCB(query);
        return listLCB;
    }

    private LinkedList<LoanedCustomerBook> createLinkedListLCB(String query) {
        LinkedList<LoanedCustomerBook> listLCB = new LinkedList<>();
        try {
            Statement st = dbConnector.getConnection().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                int idLoaned = rs.getInt("idLoaned");
                int idCustomer = rs.getInt("idCustomer");
                int idBook = rs.getInt("idBook");
                LocalDateTime loanedOn = rs.getTimestamp("loanedOn").toLocalDateTime();
                LocalDateTime returnedOn = null;
                Timestamp tsReturnedOn = rs.getTimestamp("returnedOn");
                if (tsReturnedOn != null) {
                    returnedOn = tsReturnedOn.toLocalDateTime();
                }
                boolean extraTime = rs.getBoolean("extraTime");
                LocalDateTime created_at = rs.getTimestamp("created_at").toLocalDateTime();
                LocalDateTime updated_at = rs.getTimestamp("updated_at").toLocalDateTime();
                String customerFirstName = rs.getString("firstName");
                String customerLastName = rs.getString("lastName");
                String title = rs.getString("title");
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

    public List<Integer> selectIdBooksLoaned(Statement st) throws SQLException {
        List<Integer> ids = new ArrayList<Integer>();
        String query = "SELECT idBook FROM loaned;";
        ResultSet rs = st.executeQuery(query);
        while (rs.next()) {
            int idBook = rs.getInt("idBook");
            ids.add(idBook);
        }
        return ids;
    }

    public List<Timestamp> selectBookReturned(Statement st, String column, int condition, int limit) throws SQLException {
        List<Timestamp> ids = new ArrayList<>();
        String query = "SELECT returnedOn FROM loaned WHERE " + column + "=" + condition + " ORDER BY `idLoaned` DESC LIMIT " + limit + ";";
        ResultSet rs = st.executeQuery(query);
        while (rs.next()) {
            Timestamp returnedOn = rs.getTimestamp("returnedOn");
            ids.add(returnedOn);
        }
        return ids;
    }

    public List<Timestamp> selectBookLoaned(Statement st, int idLoaned, int condition, int limit) throws SQLException {
        List<Timestamp> ids = new ArrayList<>();
        String query = "SELECT loanedOn FROM loaned WHERE idLoaned=" + idLoaned + ";";
        ResultSet rs = st.executeQuery(query);
        while (rs.next()) {
            Timestamp loanedOn = rs.getTimestamp("loanedOn");
            ids.add(loanedOn);
        }
        return ids;
    }

    public int selectCountIdCustomer(Statement st, int idCustomer) throws SQLException {
        int result = 0;
        String query = "SELECT COUNT(idCustomer) FROM loaned WHERE idCustomer=" + idCustomer + ";";
        ResultSet rs = st.executeQuery(query);
        while (rs.next()) {
            result = rs.getInt(1);
        }
        return result;
    }

    public List<Integer> selectOpenIdBookPerCustomer(Statement st, int idCustomer) throws SQLException {
        List<Integer> ids = new ArrayList<>();
        String query = "SELECT idBook FROM loaned WHERE idCustomer=" + idCustomer + " AND returnedOn='0000-00-00 00:00:00' ORDER BY `idLoaned` ASC;";
        ResultSet rs = st.executeQuery(query);
        while (rs.next()) {
            int idBook = rs.getInt("idBook");
            ids.add(idBook);
        }
        return ids;
    }

    public void updateRecordLoaned(int idCustomer, int idBook, LocalDateTime loanedOn, LocalDateTime returnedOn, boolean extraTime, int idLoaned) {
        String query1 = "update loaned set ";
        String query2 = "idCustomer = " + idCustomer + ", " +
                "idBook = " + idBook + ", " +
                "loanedOn = " + "\"" + Timestamp.valueOf(loanedOn) + "\", " +
                "returnedOn = " + "\"" + Timestamp.valueOf(returnedOn) + "\", " +
                "extraTime = " + extraTime;
        String query3 = " where idLoaned = " + idLoaned + ";";
        String query = query1 + query2 + query3;
        executeStatement(query, "Ein Datensatz wurde in der Tabelle loaned editiert/geupdated.");
    }

    public LocalDateTime selectLoanedOn(Statement st, int idCustomer, int idBook) throws SQLException {
        LocalDateTime time = LocalDateTime.now();
        String query = "select loanedOn from loaned where idCustomer=" + idCustomer + " and idBook=" + idBook + " and returnedOn='0000-00-00 00:00:00';";
        ResultSet rs = st.executeQuery(query);
        while (rs.next()) {
            Timestamp loanedOn = rs.getTimestamp("loanedOn");
            time = loanedOn.toLocalDateTime();
        }
        return (time.minusHours(2)); //wegen falscher Zeitrückgabe
    }

    //TODO query for LoanBook
    //select * from ((loaned inner join book on loaned.idBook=book.idBook) inner join author on book.idAuthor=author.idAuthor) where idCustomer=1 and loaned.idBook=31 and loaned.returnedOn>'0000-00-00 00:00:00' and book.fsk<=10 order by loaned.idLoaned DESC LIMIT 1;


}