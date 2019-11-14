package main;

import controller.Authentication;
import controller.LoanBook;
import daos.*;
import entities.BookAuthorCategory;

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
        BACDAO bacDAO = new BACDAO();

        /**
         * test start
         */
/*
        BookAuthorCategory b = new BookAuthorCategory();
        b.ausgabeListBookAuthorCategory(boDAO.getListBAC());

        for (BookAuthorCategory bac : boDAO.createLinkedListBAC("select * from ((book inner join author on book.idAuthor=author.idAuthor) inner join category on book.idCategory = category.idCategory) order by book.idBook ASC")){
            bacDAO.createRecordBAC(bac.getTitle(), bac.getIdAuthor(), bac.getFirstName(), bac.getLastName(), bac.getBirthYear(), bac.getIdCategory(), bac.getDescription(), bac.getIsbn(), bac.getFsk(), bac.getPublisher(), bac.getEdition(), bac.getFirstEdition(),
                    bac.getAmountPages(), bac.getLanguage(), bac.getIdRow(), bac.getIdColumn());
        }

 */
        /**
         * test end
         */

        int counter = 0;
        int idCustomer = 0;
        boolean firstStage = false;
        boolean secStage = false;
        do {
            System.out.println("Welcome to Lukas' Library. My name is main.BATGA, what would you like to do?");
            System.out.println("1 - log in");
            System.out.println("0 - quit");
            String input = "";
            boolean validInput = false;
            while (!validInput) {
                input = scanner.nextLine();
                validInput = input.equals("0") || input.equals("1") || input.equals("2") || input.equals("3") || input.equals("4");
                switch (input) {
                    case "0":
                        firstStage = false;
                        dbConnector.close();
                        System.exit(0);
                        break;
                    case "1":
                        idCustomer = aut.handleAuthentication(st, scanner);
                        if (idCustomer > 0){
                            counter++;
                            if (counter>0){
                                secStage = true;
                            }
                        }
                            break;
                    default:
                        System.out.println("Not a valid command.");
                        break;
                }
                do {
                    System.out.println("Hello " + CustomerDAO.selectFirstName(st, idCustomer) + "! What do you wish to do?");
                    System.out.println("1 - loan book");
                    System.out.println("2 - return book");
                    System.out.println("3 - extend loan");
                    System.out.println("4 - browse book collection");
                    System.out.println("0 - log out");
                    String input2 = scanner.nextLine();
                    switch (input2) {
                        case "0":
                            idCustomer = 0;
                            secStage = false;
                            firstStage = true;
                            System.out.println("Log out successful.");
                            break;
                        case "1":
                            if (loanBook.isAllowedToLoan(st, idCustomer)) {
                                loanBook.loanBook(st, scanner, idCustomer, loDAO, boDAO, bacDAO);
                            } else {
                                System.out.println("Sorry " + CustomerDAO.selectFirstName(st, idCustomer) + ", you already have four books currently in loan." +
                                        "please return a book first, before you loan out another one.");
                            } //TODO jump back to this menu after loaning
                            break;
                        case "2":
                            //handle return book
                            break;
                        case "3":
                            //handle extend loan
                            break;
                        case "4":
                            //extend browse book collection
                            break;
                        default:
                            System.out.println("Not a valid command.");
                            break;
                    }
                } while (secStage);
            }
        } while (firstStage);
        dbConnector.close();
        System.exit(0);
    }
}
//dbConnector.close();


