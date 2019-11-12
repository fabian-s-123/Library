import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Authentication {


    public int handleAuthentication(DBConnect con1, Connection connection) throws SQLException {
        Statement st = connection.createStatement();
        Scanner scanner = new Scanner(System.in);

        int idCustomerDB = 0;
        int triesID = 0;
        int triesPW = 0;

        while (true) {
            for (triesID = 0; triesID < 5; triesID++) {
                System.out.println("please enter you customer ID:");
                int idCustomerInput = scanner.nextInt();
                for (Integer id : CustomerDAO.selectIdCustomer(st)) {
                    if (id.equals(idCustomerInput)) {
                        idCustomerDB = id;
                        String pwDB = CustomerDAO.selectPinCode(st, idCustomerDB);
                        System.out.println("please enter your password:");
                        for (triesPW = 0; triesPW < 3; triesPW++) {
                            String pwInput = scanner.nextLine();
                            if (pwDB.equals(pwInput)) {
                                return idCustomerDB;
                            } else {
                                System.out.println("wrong password!");
                            }
                        }
                        System.out.println("too many attempts!");
                        System.exit(0);
                    } else {
                        System.out.println("no customer found with this customer ID!");

                    }
                }
            }
            System.out.println("too many attempts!");
            System.exit(0);
        }
    }
}
