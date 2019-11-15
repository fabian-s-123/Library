package controller;

import daos.BACDAO;
import daos.LoanedDAO;
import entities.BookAuthorCategory;

import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReturnBook {

    public void returnBook(Statement st, int idCustomer, BookAuthorCategory b, BACDAO bacDAO, LoanedDAO loanedDAO, Scanner scanner) throws SQLException {
        LocalDateTime now = LocalDateTime.now();
        List<Integer> loanedList = booksInLoanByCustomer(st, idCustomer, bacDAO, loanedDAO);
        System.out.println("These are the books you currently have in loan:\n");
        b.printHeadBAC();

        for (Integer x : loanedList) {
            b.printListBAC(bacDAO.selectBacId(st, x));
        }
        System.out.println();

        boolean validInput = false;
        int choice = 0;
        do {
            System.out.println("Please select the book you would like to return.");
            choice = scanner.nextInt();
            if (loanedList.contains(choice)) {
                validInput = true;
            } else {
                System.out.println("You have not loaned this book.");
            }
        } while (!validInput);
        //loanedDAO.createRecordLoanedWithReturn(idCustomer, choice, Timestamp loanedOn missing, now);
        System.out.println("Thank you for returning.");
        System.out.println("Press enter to continue");
    }

    public List<Integer> booksInLoanByCustomer (Statement st, int idCustomer, BACDAO bacDAO, LoanedDAO loanedDAO) throws SQLException {
        List<Integer> list = new ArrayList<>();
        for (Integer x : loanedDAO.selectOpenIdBookPerCustomer(st, idCustomer)){
            list.add(x);
        }
        for (Integer y : list) {
            bacDAO.selectBacId(st, y);
        }
        return list;
    }
}
