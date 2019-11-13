package controller;

import daos.BookDAO;
import daos.CustomerDAO;
import daos.LoanedDAO;

import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LoanBook {

    public void loanBook(Statement st, Scanner scanner, int idCustomer, LoanedDAO loDAO) throws SQLException {
        LocalDateTime now = LocalDateTime.now();
        System.out.println("These books are available for loaning:\n");
        List listAllBooksAfterFSKCheck;
        ArrayList<Integer> listBooksLoaned = new ArrayList<>();
        listAllBooksAfterFSKCheck = checkFSK(st, idCustomer, now);

        for (Integer x : BookDAO.selectIdBooks(st)) {
            if (LoanedDAO.selectIdBooksLoaned(st).contains(x)) {
                listBooksLoaned.add(x);
            }
        }
        for (Integer y : listBooksLoaned) {
            for (Timestamp time : LoanedDAO.selectBookReturned(st, "idCustomer", y, 1)) {
                if (time == null) {
                    listAllBooksAfterFSKCheck.remove(y);
                }
            }
        }
        for (Object z : listAllBooksAfterFSKCheck) {
            System.out.println(z.toString());
        }
        System.out.println("\nPlease select the book you would like to loan.");
        int choice = scanner.nextInt();
        loDAO.createRecordLoanedWithoutReturn(idCustomer, choice, now);
    }


    private List<Integer> checkFSK(Statement st, int idCustomer, LocalDateTime now) throws SQLException {
        Timestamp timestamp = CustomerDAO.selectBirthDay(st, idCustomer);
        LocalDateTime birthDayCustomer = timestamp.toLocalDateTime();
        List<Integer> booksAfterFSKCheck = null;
        int fsk = 0;
        if (birthDayCustomer.plusYears(18).isBefore(now)) {
            fsk = 3;
        } else if (birthDayCustomer.plusYears(10).isBefore(now) && birthDayCustomer.plusYears(18).isAfter(now)) {
            fsk = 2;
        } else if (birthDayCustomer.plusYears(10).isAfter(now)) {
            fsk = 1;
        }
        assert false;
        booksAfterFSKCheck.addAll(BookDAO.selectBooksFSK(st, fsk));
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
