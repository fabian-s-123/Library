import java.sql.Connection;

public class BookDAO extends DAO {

    public BookDAO(Connection con1) {
        super(con1);
    }

    public void createTableBook() {
        String query = "create table book (" +
                "idBook int(4) unsigned auto_increment not null, " +
                "title varchar(30) not null, " +
                "idAuthor int(4) not null, " +
                "idCategory int(2) not null, " +
                "isbn int(13),  " +
                "fsk int(2) not null, " +
                "street varchar(50) not null, " +
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
}

