package main;

import controller.Authentication;
import controller.BrowseCollection;
import controller.LoanBook;
import controller.ReturnBook;
import daos.*;
import entities.BookAuthorCategory;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class BATGA {
    public static void main(String[] args) throws SQLException {
        DBConnector dbConnector;
        dbConnector = DBConnector.getInstance();
        dbConnector.connect("w0136ee0.kasserver.com", "d03037fa", "d03037fa", "fpcQdPhv5v4UoQ6H");
        Connection connection = dbConnector.getConnection();

        Statement st = connection.createStatement();

        Scanner scanner = new Scanner(System.in);
        Authentication aut = new Authentication();
        LoanBook loanBook = new LoanBook();
        BrowseCollection browse = new BrowseCollection();
        ReturnBook returnBook = new ReturnBook();
        BookAuthorCategory b = new BookAuthorCategory();

        CustomerDAO customerDAO = new CustomerDAO();
        BookDAO bookDAO = new BookDAO();
        AuthorDAO authorDAO = new AuthorDAO();
        CategoryDAO categoryDAO = new CategoryDAO();
        LoanedDAO loanedDAO = new LoanedDAO();
        BACDAO bacDAO = new BACDAO();

        /**
         * test start
         */
        /*

         */
        /**
         * test end
         */

        int idCustomer = 0;
        boolean runProgram = true;
        boolean isAuthenticated = false;
        String input = "";
        boolean validInput = false;

        while (runProgram) {

            //Log in
            if (!isAuthenticated) {
                System.out.println("Welcome to Lukas' Library. My name is BATGA. To proceed please log in first.");
                System.out.println("1 - log in");
                System.out.println("0 - quit");
                do {
                    input = scanner.nextLine();
                    validInput = input.equals("0") || input.equals("1") || input.equals("2");
                    switch (input) {
                        case "0":
                            System.out.println("BATGA says good bye.");
                            runProgram = false;
                            validInput = true;
                            isAuthenticated = false;
                        break;
                        case "1":
                            idCustomer = aut.handleAuthentication(st, scanner, customerDAO);
                            isAuthenticated = true;
                            break;
                        default:
                            System.out.println("Not a valid command.");
                            break;
                    }
                    if (input.equals("1") || input.equals("2")) {
                        scanner.nextLine();
                    }
                } while (!validInput);
            }

            //Main Menu
            if (isAuthenticated && idCustomer > 0) {
                do {
                    System.out.println("Hello " + customerDAO.selectFirstName(st, idCustomer) + "! What do you wish to do?");
                    System.out.println("1 - loan book");
                    System.out.println("2 - return book");
                    System.out.println("3 - extend loan");
                    System.out.println("4 - browse book collection");
                    System.out.println("0 - log out");
                    String input2 = scanner.nextLine();
                    validInput = input2.equals("0") || input2.equals("1") || input2.equals("2") || input2.equals("3") || input2.equals("4");
                    switch (input2) {
                        case "0":
                            validInput = true;
                            isAuthenticated = false;
                            idCustomer = 0;
                            System.out.println("Log out successful.\nPress enter to continue.");
                            break;
                        case "1":
                            if (loanBook.isAllowedToLoan(st, idCustomer, loanedDAO)) {
                                loanBook.loanBook(st, scanner, idCustomer, loanedDAO, customerDAO, bookDAO, bacDAO, b);
                            } else {
                                System.out.println("Sorry " + customerDAO.selectFirstName(st, idCustomer) + ", you already have four books currently in loan. " +
                                        "Please return a book first, before you loan out another one.");
                            }
                            validInput = false;
                            break;
                        case "2":
                            returnBook.returnBook(st, idCustomer, b, bacDAO, loanedDAO, scanner);
                            validInput = false;
                            break;
                        case "3":
                            //handle extend loan
                            break;
                        case "4":
                            browse.browseCollection(st, bacDAO, bookDAO);
                            validInput = false;
                            break;
                        default:
                            System.out.println("Not a valid command.");
                            break;
                    }
                    scanner.nextLine();
                } while (!validInput);
            }
        }
        dbConnector.close();
    }
}



