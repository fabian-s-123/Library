package controller;

import daos.BACDAO;
import daos.BookDAO;
import entities.BookAuthorCategory;

import java.sql.SQLException;
import java.sql.Statement;

public class BrowseCollection {

    public void browseCollection(Statement st, BACDAO bacdao, BookDAO bookDAO) throws SQLException {
        System.out.println("These are all the books we have in our library. Enjoy looking through them.\n");
        BookAuthorCategory b = new BookAuthorCategory();
        b.printHeadBAC();
        for (Integer x : bookDAO.selectIdBooks(st)) {
            b.printListBAC(bacdao.selectBacId(st, x));
        }
        System.out.println();
        System.out.println("Press enter to continue");
    }
}
