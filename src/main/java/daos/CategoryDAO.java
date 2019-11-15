package daos;

import entities.Author;
import entities.Category;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.LinkedList;

public class CategoryDAO extends DAO{

    public CategoryDAO() {
    }

    public void createTablCategory() {
        String query = "create table category (" +
                "idCategory int(2) unsigned auto_increment not null, " +
                "description varchar(30) not null, " +
                "created_at timestamp default current_timestamp, " +
                "updated_at timestamp default current_timestamp on update current_timestamp, " +
                "primary key (idCategory)" +
                ");";
        executeStatement(query, "Die Tabelle category wurde angelegt.");
    }

    public void createRecordCategory(String description){
        String query1 = "insert into category (description) values (";
        String query2 = "\"" + description + "\");";
        String query = query1 + query2;
        executeStatement(query, "Ein Datensatz entities.Category wurde der Tabelle entities.Category zugefügt.");
    }
    public LinkedList<Category> getListAllCategories() {
        LinkedList<Category> listAllCategories = new LinkedList<>();
        String query = "select * from category order by category.description ASC";
        listAllCategories = createLinkedListAllCategories(query);
        return listAllCategories;
    }

    public LinkedList<Category> createLinkedListAllCategories(String query) {
        LinkedList<Category> listAC = new LinkedList<>();
        try {
            Statement st = dbConnector.getConnection().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                int idCategory = rs.getInt(1);
                String description = rs.getString(2);
                LocalDateTime created_at = rs.getTimestamp(3).toLocalDateTime();
                LocalDateTime updated_at = rs.getTimestamp(4).toLocalDateTime();
                Category temp = new Category(  idCategory, description, created_at, updated_at);
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

    public boolean checkIsIDCategoryInTable(int zuLoeschendeIdCategory) {
        boolean categoryIsInTable;
        String query = "select count(*) as anzahlDS from category where idCategory = " + zuLoeschendeIdCategory;
        int anzahlDS = zaehlenDS(query);
        if (anzahlDS > 0) {
            categoryIsInTable = true;
        } else {
            categoryIsInTable = false;
        }
        return categoryIsInTable;
    }


}