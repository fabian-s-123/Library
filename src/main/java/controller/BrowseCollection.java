package controller;

import daos.BACDAO;
import entities.BookAuthorCategory;

import java.sql.SQLException;
import java.sql.Statement;

public class BrowseCollection {

    public void browseCollection(Statement st, BACDAO bacDAO) throws SQLException {
        System.out.println("\nThese are all the books we have in our library. Enjoy looking through them.\n");
        BookAuthorCategory b = new BookAuthorCategory();
        b.printHeadBAC();
        for (BookAuthorCategory bac : bacDAO.selectAllBooksAsBAC(st)) {
            b.printListBAC(bac);
        }
        System.out.println("\nPress enter to continue (return to main menu).");
    }
}
