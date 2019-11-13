package controller;

import daos.BookDAO;
import daos.LoanedDAO;

import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LoanBook {

    public void loanBook(Statement st, Scanner scanner, int idCustomer, LoanedDAO loDAO)throws SQLException {
        System.out.println("These books are available for loaning:\n");
        List<Integer> listAllBooks = BookDAO.selectIdBooks(st);
        ArrayList<Integer> listBooksLoaned = new ArrayList<>();


        for (Integer x : BookDAO.selectIdBooks(st)) {
            if (LoanedDAO.selectIdBooksLoaned(st).contains(x)) {
                listBooksLoaned.add(x);
            }
        }
        for (Integer x : listBooksLoaned) {
            for (Timestamp time : LoanedDAO.selectBookReturned(st, x)) {
                if (time==null){
                    listAllBooks.remove(x);
                }
            }
        }
        for (Integer idBook : listAllBooks) {
            System.out.println(idBook.toString());
        }
        System.out.println("\nPlease select the book you would like to loan.");
        int choice = scanner.nextInt();


    }
}
