import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class BATGA {
    public static void main(String[] args) throws SQLException {

        DBConnect con1 = new DBConnect("w0136ee0.kasserver.com", "d03037fa", "d03037fa", "fpcQdPhv5v4UoQ6H");
        Connection connection = con1.connectDB();
        Statement st = connection.createStatement();

        Scanner scanner = new Scanner(System.in);
        Authentication aut = new Authentication();
        int idCustomer = 0;

        while (true) {
            System.out.println("Welcome to Lukas' Library. My name is BATGA, what would you like to do?");
            System.out.println("1 - log in");
            System.out.println("0 - quit");
            int choice = scanner.nextInt();
                switch (choice) {
                    case 0:
                        System.exit(0);
                    case 1:
                        idCustomer = aut.handleAuthentication(st);
                    default:
                        System.out.println("Not a valid command."); //TODO default auto-activates after handling the authentication;
            }
            if (idCustomer > 0) {
                System.out.println("Hello " + CustomerDAO.selectFirstName(st, idCustomer) + "! What do you wish to do?");
                System.out.println("1 - loan book");
                System.out.println("2 - return book");
                System.out.println("3 - extend loan");
                System.out.println("4 - browse book collection");
                System.out.println("0 - quit");
                int choice2 = scanner.nextInt();
                switch (choice2) {
                    case 0:
                        System.exit(0);
                    case 1:
                }
                con1.closeDB();
            }
        }
    }
}
