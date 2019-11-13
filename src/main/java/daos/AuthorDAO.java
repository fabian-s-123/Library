package daos;

import java.sql.Connection;

public class AuthorDAO extends DAO{

    public AuthorDAO(Connection con1) {
        super(con1);
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

    public void createRecordAuthor(String firstname, String lastname, int birthYear){
        String query1 = "insert into author (firstname, lastname, birthYear) values (";
        String query2 = "\"" + firstname + "\", " +
                "\"" + lastname + "\"," +
                + birthYear + ");";
        String query = query1 + query2;
        executeStatement(query, "Ein Datensatz entities.Author wurde der Tabelle entities.Author zugefügt.");
    }
}
