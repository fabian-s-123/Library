package controller;

import daos.BACDAO;
import daos.BookDAO;
import daos.CustomerDAO;
import daos.LoanedDAO;
import entities.Book;
import entities.BookAuthorCategory;

import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class LoanBook {

    public void loanBook(Statement st, Scanner scanner, int idCustomer, LoanedDAO loDAO, BookDAO boDAO, BACDAO bacDAO) throws SQLException {
        LocalDateTime now = LocalDateTime.now();
        System.out.println("Of course, these books are available for loaning:\n");
        List<Integer> listAllBooksAfterFSKCheck;
        ArrayList<Integer> listBooksLoaned = new ArrayList<>();
        listAllBooksAfterFSKCheck = checkFSK(st, idCustomer, now);

        for (Integer x : BookDAO.selectIdBooks(st)) {
            if (LoanedDAO.selectIdBooksLoaned(st).contains(x)) {
                listBooksLoaned.add(x);
            }
        }
        for (Integer y : listBooksLoaned) {
            for (Timestamp time : LoanedDAO.selectBookReturned(st, "idBook", y, 1)) {
                if (time == null) {
                    listAllBooksAfterFSKCheck.remove(y);
                }
            }
        }

        //printing the list of book available for this customer
        BookAuthorCategory b = new BookAuthorCategory();
        b.printHeadBAC();
        for (Integer x : listAllBooksAfterFSKCheck) {
            b.printListBAC(BACDAO.selectBacId(st, x));
        }
        System.out.println();

        //select the book for loan
        boolean validInput = false;
        int choice = 0;
        do {
            System.out.println("Please select the book you would like to loan.");
            choice = scanner.nextInt();
            if (listAllBooksAfterFSKCheck.contains(choice)) {
                validInput = true;
            } else {
                System.out.println("This book is not available.");
            }
        } while (!validInput);
        loDAO.createNewRecordLoaned(idCustomer, choice, now);
        System.out.println("BATGA is returning to menu...");
    }

    private List<Integer> checkFSK(Statement st, int idCustomer, LocalDateTime now) throws SQLException {
        Timestamp timestamp = CustomerDAO.selectBirthDay(st, idCustomer);
        LocalDateTime birthDayCustomer = timestamp.toLocalDateTime();
        List<Integer> booksAfterFSKCheck = new ArrayList<>();
        int fsk = -1;
        if (birthDayCustomer.plusYears(18).isBefore(now)) {
            fsk = 18;
        } else if (birthDayCustomer.plusYears(10).isBefore(now) && birthDayCustomer.plusYears(18).isAfter(now)) {
            fsk = 10;
        } else if (birthDayCustomer.plusYears(10).isAfter(now)) {
            fsk = 0;
        }
        for (Integer x : BookDAO.selectBooksFSK(st, fsk)) {
            booksAfterFSKCheck.add(x);
        }
        return booksAfterFSKCheck;
    }

    public boolean isAllowedToLoan(Statement st, int idCustomer) throws SQLException {
        boolean isAllowed = false;
        int booksLoanedAllTime = LoanedDAO.selectCountIdCustomer(st, idCustomer);
        if (booksLoanedAllTime >= 4) {
            int booksCurrentlyLoaned = 0;
            for (Timestamp time : LoanedDAO.selectBookReturned(st, "idCustomer", idCustomer, booksLoanedAllTime)) {
                if (time == null) {
                    booksCurrentlyLoaned++;
                }
            }
            if (booksCurrentlyLoaned < 4) {
                isAllowed = true;
            }
        } else {
            isAllowed = true;
        }
        return isAllowed;
    }
}
