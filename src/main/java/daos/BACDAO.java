package daos;

import entities.BookAuthorCategory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BACDAO extends DAO {

    public BACDAO(){
    }

    public void createTableBAC() {
        String query = "create table bac (" +
                "idBook int(4) unsigned auto_increment not null, " +
                "title varchar(50) not null, " +
                "idAuthor int(4) not null, " +
                "firstName varchar(25) not null, " +
                "lastName varchar(25) not null, " +
                "birthYear int(4) not null, " +
                "idCategory int(2) not null, " +
                "description varchar(30) not null, " +
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

    public void createRecordBAC(String title, int idAuthor, String firstName, String lastName, int birthYear, int idCategory, String description, long isbn, int fsk, String publisher,
                                 String edition, String firstEdition, int amountPages, String language, int idRow, int idColumn){
        String query1 = "insert into bac (title, idAuthor, firstName, lastName, birthYear, idCategory, description, isbn, fsk, publisher, edition, firstEdition, amountPages, language, idRow, idColumn) values (";

        String query2 = "\"" + title + "\", " +
                idAuthor + ", " +
                "\"" + firstName + "\", " +
                "\"" + lastName + "\", " +
                birthYear + ", " +
                idCategory + ", " +
                "\"" + description + "\", " +
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

    public List<BookAuthorCategory> selectBacId(Statement st, int id) throws SQLException {
        List<BookAuthorCategory> ids = new ArrayList<>();
        String query = "SELECT * FROM bac WHERE idBook=" + id + ";";
        ResultSet rs = st.executeQuery(query);
        while (rs.next())
        {
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
            //Timestamp created_at = rs.getTimestamp("created_at");
            //Timestamp updated_at = rs.getTimestamp("updated_at");
            BookAuthorCategory bac = new BookAuthorCategory(idBook, title, idAuthor, firstName, lastName, birthYear, idCategory, description, isbn, fsk, publisher, edition, firstEdition, amountPages, language, idRow, idColumn);
            ids.add(bac);
        }
        return ids;
    }
}
