package controller;

import daos.BACDAO;
import daos.BookDAO;
import daos.CustomerDAO;
import daos.LoanedDAO;
import entities.BookAuthorCategory;

import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LoanBook {

    public void loanBook(Statement st, Scanner scanner, int idCustomer, LoanedDAO loanedDAO, CustomerDAO customerDAO, BookDAO bookDAO, BACDAO bacDAO, BookAuthorCategory b) throws SQLException {
        LocalDateTime now = LocalDateTime.now();

        if (isAllowedToLoan(st, idCustomer, loanedDAO)) {
            System.out.println("Of course, these books are available for loaning:\n");
            List<Integer> listAllBooksAfterFSKCheck;
            ArrayList<Integer> listBooksLoaned = new ArrayList<>();
            listAllBooksAfterFSKCheck = checkFSK(st, idCustomer, customerDAO, now);

            for (Integer x : bookDAO.selectIdBooks(st)) {
                if (loanedDAO.selectIdBooksLoaned(st).contains(x)) {
                    listBooksLoaned.add(x);
                }
            }
            for (Integer y : listBooksLoaned) {
                for (Timestamp time : loanedDAO.selectBookReturned(st, "idBook", y, 1)) {
                    if (time == null) {
                        listAllBooksAfterFSKCheck.remove(y);
                    }
                }
            }

            //printing the list of book available for this customer
            b.printHeadBAC();
            for (Integer x : listAllBooksAfterFSKCheck) {
                b.printListBAC(bacDAO.selectBacId(st, x));
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
            loanedDAO.createNewRecordLoaned(idCustomer, choice, now);
        } else {
            System.out.println("Sorry " + customerDAO.selectFirstName(st, idCustomer) + ", you already have four books currently in loan. " +
                    "Please return a book first, before you loan out another one.");
        }
    }

    private List<Integer> checkFSK(Statement st, int idCustomer, CustomerDAO customerDAO, LocalDateTime now) throws SQLException {
        Timestamp timestamp = customerDAO.selectBirthDay(st, idCustomer);
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

    public boolean isAllowedToLoan(Statement st, int idCustomer, LoanedDAO loanedDAO) throws SQLException {
        boolean isAllowed = false;
        int booksLoanedAllTime = loanedDAO.selectCountIdCustomer(st, idCustomer);
        if (booksLoanedAllTime >= 4) {
            int booksCurrentlyLoaned = 0;
            for (Timestamp time : loanedDAO.selectBookReturned(st, "idCustomer", idCustomer, booksLoanedAllTime)) {
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
