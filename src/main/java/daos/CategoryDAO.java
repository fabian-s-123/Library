package daos;

import java.sql.Connection;

public class CategoryDAO extends DAO{

    public CategoryDAO(Connection con1) {
        super(con1);
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
}