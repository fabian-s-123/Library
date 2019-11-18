package daos;

import entities.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.LinkedList;

public class CategoryDAO extends DAO {

    public CategoryDAO() {
    }

    public void createTableCategory() {
        String query = "create table category (" +
                "idCategory int(2) unsigned auto_increment not null, " +
                "description varchar(30) not null, " +
                "created_at timestamp default current_timestamp, " +
                "updated_at timestamp default current_timestamp on update current_timestamp, " +
                "primary key (idCategory)" +
                ");";
        executeStatement(query, "Die Tabelle category wurde angelegt.");
    }

    public void createRecordCategory(String description) {
        String query1 = "insert into category (description) values (";
        String query2 = "\"" + description + "\");";
        String query = query1 + query2;
        executeStatement(query, "Ein Datensatz wurde der Tabelle Category zugefügt.");
    }

    public void updateRecordCategory(String description, int idCategory) {
        String query1 = "update category set ";
        String query2 = "description = " + "\"" + description + "\" ";
        String query3 = " where idCategory = " + idCategory + ";";
        String query = query1 + query2 + query3;
        executeStatement(query, "Ein Datensatz wurde in der Tabelle category editiert/geupdated.");
    }

    public LinkedList<Category> getListAllCategories() {
        String query = "select * from category order by category.description ASC";
        LinkedList<Category> listAllCategories = createLinkedListAllCategories(query);
        return listAllCategories;
    }

    private LinkedList<Category> createLinkedListAllCategories(String query) {
        LinkedList<Category> listAC = new LinkedList<>();
        try {
            Statement st = dbConnector.getConnection().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                int idCategory = rs.getInt("idCategory");
                String description = rs.getString("description");
                LocalDateTime created_at = rs.getTimestamp("created_at").toLocalDateTime();
                LocalDateTime updated_at = rs.getTimestamp("updated_at").toLocalDateTime();
                Category temp = new Category(idCategory, description, created_at, updated_at);
                listAC.add(temp);
            }
            st.close();
        } catch (SQLException sqle) {
            System.out.println("SQLException: " + sqle.getMessage());
            System.out.println("SQLState: " + sqle.getSQLState());
            System.out.println("VendorError: " + sqle.getErrorCode());
            sqle.printStackTrace();
        }
        return listAC;
    }
}