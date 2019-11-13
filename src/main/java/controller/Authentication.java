package controller;

import daos.CustomerDAO;
import main.DBConnector;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Authentication {

    public int handleAuthentication(Statement st, Scanner scanner) throws SQLException {
        DBConnector dbConnector;
        dbConnector = DBConnector.getInstance();
        int idCustomerDB = checkIdCustomer(st, scanner, dbConnector);
        checkPinCode(st, idCustomerDB, scanner, dbConnector);
        System.out.println("Authentication successful.");
        return idCustomerDB;
    }

    private int checkIdCustomer(Statement st, Scanner scanner, DBConnector dbConnector) throws SQLException {
        int idCustomerDB = 0;
        System.out.println("Please enter your customer ID:");
        for (int i = 0; i < 5; i++) {
            int idCustomerInput = scanner.nextInt();
            if (CustomerDAO.selectIdCustomer(st).contains(idCustomerInput)) {
                idCustomerDB = idCustomerInput;
                break;
            } else {
                System.out.println("Sorry, no customer found with this ID.");
                if (i == 4) {
                    System.out.println("Too many attempts!");
                    dbConnector.close();
                    System.exit(0);
                }
            }
        }
        return idCustomerDB;
    }

    private void checkPinCode(Statement st, int idCustomer, Scanner scanner, DBConnector dbConnector) throws SQLException {
        System.out.println("Please enter your pin code:");
        for (int i = 0; i < 3; i++) {
            String pinCodeInput = scanner.next();
            String pinCodeDB = CustomerDAO.selectPinCode(st, idCustomer);
            if (pinCodeInput.equals(pinCodeDB)) {
                System.out.println("Pin code correct.");
                break;
            } else {
                System.out.println("Wrong pin code!");
                if (i == 2) {
                    System.out.println("Too many attempts!");
                    dbConnector.close();
                    System.exit(0);
                }
            }
        }
    }
}
