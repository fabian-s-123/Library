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

    public void loanBook(Statement st, Scanner scanner, int idCustomer, LoanedDAO loanedDAO, CustomerDAO customerDAO, BACDAO bacDAO, BookAuthorCategory b) throws SQLException {
        LocalDateTime now = LocalDateTime.now();
        List<BookAuthorCategory> listOfBooks;
        List<Integer> listOfBooksLoaned;
        List<BookAuthorCategory> listOfBooksToRemove = new ArrayList<>();

        if (isAllowedToLoan(st, idCustomer, bacDAO)) {
            System.out.println("\nOf course, these books are available for loaning:\n");

            //list of books available for loaning after FSK check
            listOfBooks = bacDAO.selectBooksAvailableFSKSortedAsBAC(st, checkFSK(st, idCustomer, customerDAO, now));
            //list of book IDs currently loaned out
            listOfBooksLoaned = loanedDAO.selectIdBooksLoaned(st);

            //removing books from listOfBook that are currently loaned out
            for (Integer x : listOfBooksLoaned) {
                for (BookAuthorCategory c : listOfBooks){
                    if (c.getIdBook()==x) {
                        listOfBooksToRemove.add(c);
                    }
                }
            }
            listOfBooks.removeAll(listOfBooksToRemove);

            //print the available books
            b.printHeadBAC();
            b.printListBACList(listOfBooks);

            //validating the book id to return
            boolean validInput = false;
            int choice = 0;
            BookAuthorCategory temp = new BookAuthorCategory();
            do {
                System.out.println("\n\nPlease select the book you would like to loan.  |  0 - quit loan book (return to main menu)");
                choice = scanner.nextInt();
                for (BookAuthorCategory i : listOfBooks) {
                    if (i.getIdBook() == choice) {
                        temp = i;
                        validInput = true;
                    } else if (choice==0) {
                        validInput= true;
                        System.out.println();
                        break;
                    }
                }
                if (!validInput) {
                    System.out.println("This book is not available.");
                }
            } while (!validInput);

            //creating entry in DB
            if (choice > 0) {
                loanedDAO.createNewRecordLoaned(idCustomer, temp.getIdBook(), now);
                System.out.println("One moment please... Here is your book.\n");
            }
            //if isAllowedToLoan = false
        } else {
            System.out.println("Sorry " + customerDAO.selectFirstName(st, idCustomer) + ", you already have four books currently in loan. " +
                    "Please return a book first, before you loaning another one.");
        }
    }

    private Integer checkFSK(Statement st, int idCustomer, CustomerDAO customerDAO, LocalDateTime now) throws SQLException {
        Timestamp timestamp = customerDAO.selectBirthDay(st, idCustomer);
        LocalDateTime birthDayCustomer = timestamp.toLocalDateTime();
        int fsk = -1;
        if (birthDayCustomer.plusYears(18).isBefore(now)) {
            fsk = 18;
        } else if (birthDayCustomer.plusYears(10).isBefore(now) && birthDayCustomer.plusYears(18).isAfter(now)) {
            fsk = 10;
        } else if (birthDayCustomer.plusYears(10).isAfter(now)) {
            fsk = 0;
        }
        return fsk;
    }

    private boolean isAllowedToLoan(Statement st, int idCustomer, BACDAO bacDAO) throws SQLException {
        boolean isAllowed = false;
        if (bacDAO.selectBooksLoanedPerCustomerAsBAC(st, idCustomer).size() < 4) {
            isAllowed = true;
        }
        return isAllowed;
    }
}
