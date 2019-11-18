package daos;

import entities.*;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BookDAO extends DAO {

    public BookDAO() {
    }

    public void createTableBook() {
        String query = "create table book (" +
                "idBook int(4) unsigned auto_increment not null, " +
                "title varchar(50) not null, " +
                "idAuthor int(4) not null, " +
                "idCategory int(2) not null, " +
                "isbn bigint(13),  " +
                "fsk int(2) not null, " +
                "publisher varchar(30) not null, " +
                "edition varchar(10) not null, " +
                "firstEdition varchar(10), " +
                "amountPages int(3) not null, " +
                "language varchar(15) not null, " +
                "idRow int(2) not null, " +
                "idColumn int(2) not null, " +
                "created_at timestamp default current_timestamp, " +
                "updated_at timestamp default current_timestamp on update current_timestamp, " +
                "primary key (idBook)" +
                ");";
        executeStatement(query, "Die Tabelle book wurde angelegt.");
    }

    public void createRecordBook(String title, int idAuthor, int idCategory, long isbn, int fsk, String publisher,
                                 String edition, String firstEdition, int amountPages, String language, int idRow, int idColumn) {
        String query1 = "insert into book (title, idAuthor, idCategory, isbn, fsk, publisher, edition, firstEdition, amountPages, language, idRow, idColumn) values (";
        String query2 = "\"" + title + "\", " +
                idAuthor + ", " +
                idCategory + ", " +
                isbn + ", " +
                fsk + ", " +
                "\"" + publisher + "\", " +
                "\"" + edition + "\", " +
                "\"" + firstEdition + "\", " +
                amountPages + ", " +
                "\"" + language + "\", " +
                idRow + ", " +
                idColumn + ");";
        String query = query1 + query2;
        executeStatement(query, "Ein Datensatz der Tabelle book zugefügt.");
    }

    public void updateRecordBook(String title, int idAuthor, int idCategory, long isbn, int fsk, String publisher,
                                 String edition, String firstEdition, int amountPages, String language, int idRow, int idColumn, int idBook) {
        String query1 = "update book set ";
        String query2 = "title = " + "\"" + title + "\", " +
                "idAuthor = " + idAuthor + ", " +
                "idCategory = " + idCategory + ", " +
                "isbn = " + isbn + ", " +
                "fsk = " + fsk + ", " +
                "publisher = " + "\"" + publisher + "\", " +
                "edition = " + "\"" + edition + "\", " +
                "firstEdition = " + "\"" + firstEdition + "\", " +
                "amountPages = " + amountPages + ", " +
                "language = " + "\"" + language + "\", " +
                "idRow = " + idRow + ", " +
                "idColumn = " + idColumn;
        String query3 = " where idBook = " + idBook + ";";
        String query = query1 + query2 + query3;
        executeStatement(query, "Ein Datensatz wurde in der Tabelle book geändert.");
    }

    public List<Integer> selectIdBooks(Statement st) throws SQLException {
        List<Integer> ids = new ArrayList<Integer>();
        String query = "SELECT idBook FROM book;";
        ResultSet rs = st.executeQuery(query);
        while (rs.next()) {
            int idBook = rs.getInt("idBook");
            ids.add(idBook);
        }
        return ids;
    }

    public static List<Integer> selectBooksFSK(Statement st, int fsk) throws SQLException {
        List<Integer> ids = new ArrayList<>();
        String query = "SELECT idBook FROM book WHERE fsk<=" + fsk + ";";
        ResultSet rs = st.executeQuery(query);
        while (rs.next()) {
            int idBook = rs.getInt("idBook");
            ids.add(idBook);
        }
        return ids;
    }

    public LinkedList<BookAuthorCategory> getListBAC() {
        LinkedList<BookAuthorCategory> listBAC = new LinkedList<>();
        String query = "select * from ((book inner join author on book.idAuthor=author.idAuthor) inner join category on book.idCategory = category.idCategory) order by book.idBook ASC";
        listBAC = createLinkedListBAC(query);
        return listBAC;
    }

    private LinkedList<BookAuthorCategory> createLinkedListBAC(String query) {
        LinkedList<BookAuthorCategory> listBAC = new LinkedList<>();
        try {
            Statement st = dbConnector.getConnection().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                int idBook = rs.getInt("idBook");
                String title = rs.getString("title");
                int idAuthor = rs.getInt("idAuthor");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                int birthYear = rs.getInt("birthYear");
                int idCategory = rs.getInt("idCategory");
                String description = rs.getString("description");
                long isbn = rs.getLong("isbn");
                int fsk = rs.getInt("fsk");
                String publisher = rs.getString("publisher");
                String edition = rs.getString("edition");
                String firstEdition = rs.getString("firstEdition");
                int amountPages = rs.getInt("amountPages");
                String language = rs.getString("language");
                int idRow = rs.getInt("idRow");
                int idColumn = rs.getInt("idColumn");
                LocalDateTime created_at = rs.getTimestamp("created_at").toLocalDateTime();
                LocalDateTime updated_at = rs.getTimestamp("updated_at").toLocalDateTime();
                BookAuthorCategory temp = new BookAuthorCategory(idBook, title, idAuthor, firstName, lastName, birthYear, idCategory, description, isbn, fsk, publisher, edition, firstEdition, amountPages, language, idRow, idColumn, created_at, updated_at);
                listBAC.add(temp);
            }
            st.close();
        } catch (SQLException sqle) {
            System.out.println("SQLException: " + sqle.getMessage());
            System.out.println("SQLState: " + sqle.getSQLState());
            System.out.println("VendorError: " + sqle.getErrorCode());
            sqle.printStackTrace();
        }
        return listBAC;
    }

    public LinkedList<BookAuthorCategory> getListAuthorBook() {
        LinkedList<BookAuthorCategory> listAuthorBook = new LinkedList<>();
        String query = "select * from ((author inner join book As book on author.idAuthor=book.idAuthor) inner join category on book.idCategory = category.idCategory) order by author.lastName ASC";
        listAuthorBook = createLinkedListABC(query);
        return listAuthorBook;
    }

    private LinkedList<BookAuthorCategory> createLinkedListABC(String query) {
        LinkedList<BookAuthorCategory> listABC = new LinkedList<>();
        try {
            Statement st = dbConnector.getConnection().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                int idAuthor = rs.getInt("idAuthor");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                int birthYear = rs.getInt("birthYear");
                int idBook = rs.getInt("idBook");
                String title = rs.getString("title");
                int idCategory = rs.getInt("idCategory");
                String description = rs.getString("description");
                long isbn = rs.getLong("isbn");
                int fsk = rs.getInt("fsk");
                String publisher = rs.getString("publisher");
                String edition = rs.getString("edition");
                String firstEdition = rs.getString("firstEdition");
                int amountPages = rs.getInt("amountPages");
                String language = rs.getString("language");
                int idRow = rs.getInt("idRow");
                int idColumn = rs.getInt("idColumn");
                LocalDateTime created_at = rs.getTimestamp("updated_at").toLocalDateTime();
                LocalDateTime updated_at = rs.getTimestamp("created_at").toLocalDateTime();
                BookAuthorCategory temp = new BookAuthorCategory(idBook, title, idAuthor, firstName, lastName, birthYear, idCategory, description, isbn, fsk, publisher, edition, firstEdition, amountPages, language, idRow, idColumn, created_at, updated_at);
                listABC.add(temp);
            }
            st.close();
        } catch (SQLException sqle) {
            System.out.println("SQLException: " + sqle.getMessage());
            System.out.println("SQLState: " + sqle.getSQLState());
            System.out.println("VendorError: " + sqle.getErrorCode());
            sqle.printStackTrace();
        }
        return listABC;
    }

    public LinkedList<BookAuthorCategory> getListCategoryBook() {
        LinkedList<BookAuthorCategory> listCategoryBook = new LinkedList<>();
        String query = "select * from ((category inner join book on category.idCategory=book.idCategory) inner join author on book.idAuthor = author.idAuthor) order by category.description ASC";
        listCategoryBook = createLinkedListCBA(query);
        return listCategoryBook;
    }

    public LinkedList<BookAuthorCategory> getListLanguageBook() {
        LinkedList<BookAuthorCategory> listLanguageBook = new LinkedList<>();
        String query = "select * from ((category inner join book on category.idCategory=book.idCategory) inner join author on book.idAuthor = author.idAuthor) order by book.language, author.lastName ASC";
        listLanguageBook = createLinkedListCBA(query);
        return listLanguageBook;
    }

    private LinkedList<BookAuthorCategory> createLinkedListCBA(String query) {
        LinkedList<BookAuthorCategory> listCBA = new LinkedList<>();
        try {
            Statement st = dbConnector.getConnection().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                int idCategory = rs.getInt("idCategory");
                String description = rs.getString("description");
                LocalDateTime created_at = rs.getTimestamp("created_at").toLocalDateTime();
                LocalDateTime updated_at = rs.getTimestamp("updated_at").toLocalDateTime();
                int idBook = rs.getInt("idBook");
                String title = rs.getString("title");
                int idAuthor = rs.getInt("idAuthor");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                int birthYear = rs.getInt("birthYear");
                long isbn = rs.getLong("isbn");
                int fsk = rs.getInt("fsk");
                String publisher = rs.getString("publisher");
                String edition = rs.getString("edition");
                String firstEdition = rs.getString("firstEdition");
                int amountPages = rs.getInt("amountPages");
                String language = rs.getString("language");
                int idRow = rs.getInt("idRow");
                int idColumn = rs.getInt("idColumn");
                BookAuthorCategory temp = new BookAuthorCategory(idBook, title, idAuthor, firstName, lastName, birthYear, idCategory, description, isbn, fsk, publisher, edition, firstEdition, amountPages, language, idRow, idColumn, created_at, updated_at);
                listCBA.add(temp);
            }
            st.close();
        } catch (SQLException sqle) {
            System.out.println("SQLException: " + sqle.getMessage());
            System.out.println("SQLState: " + sqle.getSQLState());
            System.out.println("VendorError: " + sqle.getErrorCode());
            sqle.printStackTrace();
        }
        return listCBA;
    }
}

