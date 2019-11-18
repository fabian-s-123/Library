package daos;

import entities.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BACDAO extends DAO {

    public BACDAO() {
    }

    public List<BookAuthorCategory> selectAllBooksAsBAC(Statement st) throws SQLException {
        List<BookAuthorCategory> ids = new ArrayList<>();
        String query = "select * from ((book inner join category on book.idCategory=category.idCategory)  inner join author on book.idAuthor=author.idAuthor) order by book.idBook ASC;";
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
            Timestamp created_at = rs.getTimestamp("created_at");
            Timestamp updated_at = rs.getTimestamp("updated_at");
            BookAuthorCategory bac = new BookAuthorCategory(idBook, title, idAuthor, firstName, lastName, birthYear, idCategory, description, isbn, fsk, publisher, edition, firstEdition, amountPages, language, idRow, idColumn, created_at.toLocalDateTime(), updated_at.toLocalDateTime());
            ids.add(bac);
        }
        return ids;
    }

    public List<BookAuthorCategory> selectBooksLoanedPerCustomerAsBAC(Statement st, int idCustomer) throws SQLException {
        List<BookAuthorCategory> list = new ArrayList<>();
        String query = "select * from (((loaned inner join book on loaned.idBook=book.idBook) inner join category on book.idCategory=category.idCategory) inner join author on book.idAuthor=author.idAuthor) where idCustomer=" + idCustomer + " and loaned.returnedOn='0000-00-00 00:00:00' order by loaned.idLoaned ASC;";
        ResultSet rs = st.executeQuery(query);
        while (rs.next()){
            int bookId = rs.getInt("idBook");
            String title = rs.getString("title");
            int idAuthor = rs.getInt("idAuthor");
            String firstName = rs.getString("firstName");
            String lastName = rs.getString("lastName");
            int birthYear = rs.getInt("birthYear");
            int idCategory = rs.getInt("idCategory");
            String description = rs.getString("description");
            long isbn = rs.getLong("isbn");
            int fsk = rs.getInt("fsk");
            Timestamp loanedOn = rs.getTimestamp("loanedOn");
            Timestamp returnedOn = rs. getTimestamp("returnedOn");
            String publisher = rs.getString("publisher");
            String edition = rs.getString("edition");
            String firstEdition = rs.getString("firstEdition");
            int amountPages = rs.getInt("amountPages");
            String language = rs.getString("language");
            int idRow = rs.getInt("idRow");
            int idColumn = rs.getInt("idColumn");
            //Timestamp created_at = rs.getTimestamp("created_at");
            //Timestamp updated_at = rs.getTimestamp("updated_at");
            BookAuthorCategory bac = new BookAuthorCategory (bookId, title, idAuthor, firstName, lastName, birthYear, idCategory, description, isbn, fsk, loanedOn, returnedOn, publisher, edition, firstEdition, amountPages, language, idRow, idColumn);
            list.add(bac);
        }
        return list;
    }

    public List<BookAuthorCategory> selectBooksAvailableFSKSortedAsBAC(Statement st, int fsk) throws SQLException {
        List<BookAuthorCategory> list = new ArrayList<>();
        String query = "select * from ((book inner join category on book.idCategory=category.idCategory) inner join author on book.idAuthor=author.idAuthor) where book.fsk<=" + fsk + " ORDER BY `book`.`idBook` ASC";
        ResultSet rs = st.executeQuery(query);
        while (rs.next()){
            int bookId = rs.getInt("idBook");
            String title = rs.getString("title");
            int idAuthor = rs.getInt("idAuthor");
            String firstName = rs.getString("firstName");
            String lastName = rs.getString("lastName");
            int birthYear = rs.getInt("birthYear");
            int idCategory = rs.getInt("idCategory");
            String description = rs.getString("description");
            long isbn = rs.getLong("isbn");
            int fskN = rs.getInt("fsk");
            String publisher = rs.getString("publisher");
            String edition = rs.getString("edition");
            String firstEdition = rs.getString("firstEdition");
            int amountPages = rs.getInt("amountPages");
            String language = rs.getString("language");
            int idRow = rs.getInt("idRow");
            int idColumn = rs.getInt("idColumn");
            Timestamp created_at = rs.getTimestamp("created_at");
            Timestamp updated_at = rs.getTimestamp("updated_at");
            BookAuthorCategory bac = new BookAuthorCategory (bookId, title, idAuthor, firstName, lastName, birthYear, idCategory, description, isbn, fskN, publisher, edition, firstEdition, amountPages, language, idRow, idColumn, created_at.toLocalDateTime(), updated_at.toLocalDateTime());
            list.add(bac);
        }
        return list;
    }
}
