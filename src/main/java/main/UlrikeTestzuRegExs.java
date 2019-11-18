package main;

import java.util.Scanner;
import java.util.regex.Pattern;

public class UlrikeTestzuRegExs {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Bitte gib etwas ein: ");
        String eingabe = sc.nextLine();
        System.out.println("Deine Eingabe enthält nur Zahlen: " + (Pattern.matches("\\d*", eingabe)));

    }
}
