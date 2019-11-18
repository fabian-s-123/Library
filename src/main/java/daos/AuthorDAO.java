package daos;

import entities.*;

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
        executeStatement(query, "Die Tabelle Author wurde angelegt.");
    }

    public void createRecordAuthor(String firstname, String lastname, int birthYear) {
        String query1 = "insert into author (firstname, lastname, birthYear) values (";
        String query2 = "\"" + firstname + "\", " +
                "\"" + lastname + "\"," +
                +birthYear + ");";
        String query = query1 + query2;
        executeStatement(query, "Ein Datensatz wurde der Tabelle Author zugefügt.");
    }

    public void updateRecordAuthor(String firstname, String lastname, int birthYear, int idAuthor) {
        String query1 = "update author set ";
        String query2 = "firstName = " + "\"" + firstname + "\", " +
                "lastname = " + "\"" + lastname + "\"," +
                "birthYear = " + birthYear;
        String query3 = " where idAuthor = " + idAuthor + ";";
        String query = query1 + query2 + query3;
        executeStatement(query, "Ein Datensatz wurde in der Tabelle Author editiert/geupdated.");
    }


    public LinkedList<Author> getListAllAuthors() {
        String query = "select * from author order by author.lastName ASC";
        LinkedList<Author> listAllAuthors = createLinkedListAllAuthors(query);
        return listAllAuthors;
    }

    private LinkedList<Author> createLinkedListAllAuthors(String query) {
        LinkedList<Author> listAA = new LinkedList<>();
        try {
            Statement st = dbConnector.getConnection().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                int idAuthor = rs.getInt("idAuthor");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                int birthYear = rs.getInt("birthYear");
                LocalDateTime created_at = rs.getTimestamp("created_at").toLocalDateTime();
                LocalDateTime updated_at = rs.getTimestamp("updated_at").toLocalDateTime();
                Author temp = new Author(idAuthor, firstName, lastName, birthYear, created_at, updated_at);
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
}