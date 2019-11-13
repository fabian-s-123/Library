package main;

import controller.Authentication;
import controller.LoanBook;
import daos.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
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

        CustomerDAO cuDAO = new CustomerDAO();
        BookDAO boDAO = new BookDAO();
        AuthorDAO auDAO = new AuthorDAO();
        CategoryDAO caDAO = new CategoryDAO();
        LoanedDAO loDAO = new LoanedDAO();

        /**
         * test start
         */

        /**
         * test end
         */


        int idCustomer = 0;

        while (true) {
            System.out.println("Welcome to Lukas' Library. My name is main.BATGA, what would you like to do?");
            System.out.println("1 - log in");
            System.out.println("0 - quit");
            int choice = scanner.nextInt();
            switch (choice) {
                case 0:
                    dbConnector.close();
                    System.exit(0);
                    break;
                case 1:
                    idCustomer = aut.handleAuthentication(st, scanner);
                    break;
                default:
                    System.out.println("Not a valid command.");
                    break;
            }
            if (idCustomer > 0) {
                System.out.println("Hello " + CustomerDAO.selectFirstName(st, idCustomer) + "! What do you wish to do?");
                System.out.println("1 - loan book");
                System.out.println("2 - return book");
                System.out.println("3 - extend loan");
                System.out.println("4 - browse book collection");
                System.out.println("0 - log out");
                int choice2 = scanner.nextInt();
                switch (choice2) {
                    case 0:
                        idCustomer = 0;
                        break;
                    case 1:
                        if (loanBook.isAllowedToLoan(st, idCustomer)){
                            loanBook.loanBook(st, scanner, idCustomer, loDAO);
                        } else {
                            System.out.println("Sorry " + CustomerDAO.selectFirstName(st, idCustomer) + ", you already have four books currently in loan." +
                                    "please return a book first, before you loan out another one.");
                        }
                         break;
                    case 2:
                        //handle return book
                        break;
                    case 3:
                        //handle extend loan
                        break;
                    case 4:
                        //extend browse book collection
                        break;
                    default:
                        System.out.println("Not a valid command.");
                        break;
                }
            }
            dbConnector.close();
        }
    }
}
