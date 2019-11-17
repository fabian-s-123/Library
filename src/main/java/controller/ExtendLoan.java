package controller;

import daos.BACDAO;
import daos.LoanedDAO;
import entities.BookAuthorCategory;

import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class ExtendLoan {

    public void extendLoan (Statement st, Scanner scanner, ReturnBook returnBook, int idCustomer, BookAuthorCategory b, BACDAO bacDAO, LoanedDAO loanedDAO) throws SQLException {
        LocalDateTime now = LocalDateTime.now();
        List<Integer> loanedList = returnBook.booksInLoanByCustomer(st, idCustomer, bacDAO, loanedDAO);
        System.out.println("These are the books you currently have in loan:\n");
        b.printHeadBAC();

        for (Integer x : loanedList) {
            b.printListBAC(bacDAO.selectBacId(st, x));
        }
        System.out.println();

        boolean validInput = false;
        int choice = 0;
        do {
            System.out.println("Please select the book you would like to extend the loan for.");
            choice = scanner.nextInt();
            if (loanedList.contains(choice)) {
                validInput = true;
            } else {
                System.out.println("You have not loaned this book.");
            }
        } while (!validInput);
        if (choice > 0) {
            loanedDAO.returnBook(idCustomer, choice, loanedDAO.selectLoanedOn(st, idCustomer, choice) , now);
            loanedDAO.createRecordLoanedWithExtraTime(idCustomer, choice, now);
        }
    }
}
