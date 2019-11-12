import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Authentication {


    public void handleAuthentication() throws SQLException {
        DBConnect con1 = new DBConnect("w0136ee0.kasserver.com", "d03037fa", "d03037fa", "fpcQdPhv5v4UoQ6H");
        Connection connection = con1.connectDB();
        Statement st = connection.createStatement();
        Scanner scanner = new Scanner(System.in);




        System.out.println("please enter you customer ID:");
        int idCustomerInput = scanner.nextInt();
        int idCustomerDB = 0;



        for (Integer id : CustomerDAO.selectIdCustomer(st)) {
            if (idCustomerInput==id) {
                idCustomerDB = id;
            } else {
                System.out.println("no customer found with this customer ID!");
            }
        }

        String pwDB = CustomerDAO.selectPinCode(st, idCustomerDB);
        System.out.println("please enter your password:");
        int tries = 0;


        for (tries = 0; tries <= 3; tries++) {
            String pwInput = scanner.nextLine();
            if (pwDB.equals(pwInput)) {

            }
        }
    }
}
