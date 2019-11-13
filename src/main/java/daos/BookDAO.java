package daos;

import entities.Book;

import java.sql.*;
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
        executeStatement(query, "Die Tabelle customer wurde angelegt.");
    }

    public void createRecordBook(String title, int idAuthor, int idCategory, long isbn, int fsk, String publisher,
                                     String edition, String firstEdition, int amountPages, String language, int idRow, int idColumn){
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
        executeStatement(query, "Ein Datensatz book der Tabelle book zugefügt.");
    }

    public static List<Integer> selectIdBooks(Statement st) throws SQLException {
        List<Integer> ids = new ArrayList<Integer>();
        String query = "SELECT idBook FROM book;";
        ResultSet rs = st.executeQuery(query);
        while (rs.next())
        {
            int idBook = rs.getInt("idBook");
            ids.add(idBook);
        }
        return ids;
    }

    public static List<Integer> selectBooksFSK(Statement st, int fsk) throws SQLException {
        List<Integer> ids = new ArrayList<>();
        String query = "SELECT idBook FROM book WHERE fsk<=" + fsk + ";";
        ResultSet rs = st.executeQuery(query);
        while (rs.next())
        {
            int idBook = rs.getInt("idBook");
            ids.add(idBook);
        }
        return ids;
    }
}

