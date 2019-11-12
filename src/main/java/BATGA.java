import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class BATGA {
    public static void main(String[] args) throws SQLException {

        DBConnect con1 = new DBConnect("w0136ee0.kasserver.com", "d03037fa", "d03037fa", "fpcQdPhv5v4UoQ6H");
        Connection connection = con1.connectDB();
        Scanner scanner = new Scanner(System.in);
        Authentication aut = new Authentication();
        int idCustomer = 0;

        while (true) {
            System.out.println("Welcome to Lukas' Library. My name is BATGA, what would you like to do?");
            System.out.println("1 - log in");
            System.out.println("0 - quit");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 0:
                    System.exit(0);
                case 1:
                    idCustomer = aut.handleAuthentication(con1, connection);
                default:
                    System.out.println("wrong input");
            }
            System.out.println("Log in successful. What do you wish to do?");
            System.out.println("1 - loan book");
            System.out.println("2 - return book");
            System.out.println("3 - extend loan");
            System.out.println("4 - browse book collection");
            System.out.println("0 - quit");
            int choice2 = Integer.parseInt(scanner.nextLine());
            switch (choice2) {
                case 0:
                    System.exit(0);
                case 1:
            }
            con1.closeDB();
        }
    }
}
