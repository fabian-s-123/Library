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

public class ExtendLoan {

    public void extendLoan(Statement st, Scanner scanner, int idCustomer, BookAuthorCategory b, BACDAO bacDAO, LoanedDAO loanedDAO) throws SQLException {
        LocalDateTime now = LocalDateTime.now();
        List<BookAuthorCategory> listOfBooks = new ArrayList<>();

        //check if customer has any books in loan at the moment
        if (bacDAO.selectBooksLoanedPerCustomerAsBAC(st, idCustomer).size() > 0) {

            System.out.println("\nThese are the books you currently have in loan:\n");

            //print all the books the customer has in loan & add list of book ids the customer has in loan to ArrayList
            b.printHeadBAC();
            for (BookAuthorCategory bac : bacDAO.selectBooksLoanedPerCustomerAsBAC(st, idCustomer)) {
                b.printListBAC(bac);
                listOfBooks.add(bac);
            }

            //validating the book id to return
            boolean validInput = false;
            int choice = 0;
            BookAuthorCategory temp = new BookAuthorCategory();
            do {
                System.out.println("\n\nPlease select the book you would like to extend the loan for.  |  0 - quit extend loan (return to main menu)");
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
                    System.out.println("You have not loaned this book.");
                }
            } while (!validInput);

            //updating loan with returnedOn & add new entry with annotation extra time
            if (choice > 0) {
                loanedDAO.returnBook(idCustomer, choice, temp.getLoanedOn().toLocalDateTime(), now);
                loanedDAO.createRecordLoanedWithExtraTime(idCustomer, choice, now);
                System.out.println("The loan has been extended.\n");
            }
        } else {
            System.out.println("You do not have any books in loan at the moment.");
        }
    }
}
