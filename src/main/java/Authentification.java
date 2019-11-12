import java.util.Scanner;

public class Authentification {

    
    public void handleAuthentication() {
        DBConnect con1 = new DBConnect("w0136ee0.kasserver.com", "d03037fa", "d03037fa", "fpcQdPhv5v4UoQ6H");
        con1.connectDB();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1 - Log in");
            System.out.println("0 - quit");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 0:
                    break;
                case 1:
                    System.out.println("please enter you customer ID:");


            }
        }
    }
}
