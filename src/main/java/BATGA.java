import java.util.Scanner;

public class BATGA {
    public static void main(String[] args) {

        System.out.println("Hier kommt alles was das BATGA kann hinein");

        boolean system = true;
        Scanner scanner = new Scanner(System.in);
        Authentification aut = new Authentification();

        while (system) {
            System.out.println("1 - Log in");
            System.out.println("0 - quit");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 0:
                    break;
                case 1:
                    aut.handleAuthentication();
            }
        }
    }
}
