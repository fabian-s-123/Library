package daos;

import entities.Author;
import entities.BookAuthorCategory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.LinkedList;

public class AuthorDAO extends DAO {

    public AuthorDAO() {
    }

    public void createTableAuthor() {
        String query = "create table author (" +
                "idAuthor int(4) unsigned auto_increment not null, " +
                "firstName varchar(25) not null, " +
                "lastName varchar(25) not null, " +
                "birthYear int(4) not null, " +
                "created_at timestamp default current_timestamp, " +
                "updated_at timestamp default current_timestamp on update current_timestamp, " +
                "primary key (idAuthor)" +
                ");";
        executeStatement(query, "Die Tabelle entities.Author wurde angelegt.");
    }

    public void createRecordAuthor(String firstname, String lastname, int birthYear) {
        String query1 = "insert into author (firstname, lastname, birthYear) values (";
        String query2 = "\"" + firstname + "\", " +
                "\"" + lastname + "\"," +
                +birthYear + ");";
        String query = query1 + query2;
        executeStatement(query, "Ein Datensatz entities.Author wurde der Tabelle entities.Author zugefügt.");
    }

    public LinkedList<Author> getListAllAuthors() {
        LinkedList<Author> listAllAuthors = new LinkedList<>();
        String query = "select * from author order by author.lastName ASC";
        listAllAuthors = createLinkedListAllAuthors(query);
        return listAllAuthors;
    }

    public LinkedList<Author> createLinkedListAllAuthors(String query) {
        LinkedList<Author> listAA = new LinkedList<>();
        try {
            Statement st = dbConnector.getConnection().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                int idAuthor = rs.getInt(1);
                String firstName = rs.getString(2);
                String lastName = rs.getString(3);
                int birthYear = rs.getInt(4);
                LocalDateTime created_at = rs.getTimestamp(5).toLocalDateTime();
                LocalDateTime updated_at = rs.getTimestamp(6).toLocalDateTime();
                Author temp = new Author(  idAuthor, firstName, lastName, birthYear, created_at, updated_at);
                listAA.add(temp);
            }
            st.close();
        } catch (SQLException sqle) {
            System.out.println("SQLException: " + sqle.getMessage());
            System.out.println("SQLState: " + sqle.getSQLState());
            System.out.println("VendorError: " + sqle.getErrorCode());
            sqle.printStackTrace();
        }
        return listAA;
    }

    public boolean checkIsIDAuthorInTable(int zuLoeschendeIdAuthor) {
        boolean authorIsInTable;
        String query = "select count(*) as anzahlDS from author where idAuthor = " + zuLoeschendeIdAuthor;
        int anzahlDS = zaehlenDS(query);
        if (anzahlDS > 0) {
            authorIsInTable = true;
        } else {
            authorIsInTable = false;
        }
        return authorIsInTable;
    }
}