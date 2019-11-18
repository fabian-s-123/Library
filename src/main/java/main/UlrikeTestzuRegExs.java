package main;

import java.util.Scanner;
import java.util.regex.Pattern;

public class UlrikeTestzuRegExs {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Bitte gib etwas ein: ");
        String eingabe = sc.nextLine();
        System.out.println("Deine Eingabe enthält nur Zahlen: mit \\d* " + (Pattern.matches("\\d*", eingabe)));
        System.out.println("Deine Eingabe enthält nur Zahlen: mit [0-9]*; das * = keinmal oder beliebig oft: " + (Pattern.matches("[0-9]*", eingabe)));
        do {
            System.out.println("Mit [yjn] könnte man die abfragen auf j/n vereinfachen - oder?");
            eingabe = sc.nextLine();
            System.out.println("Deine Eingabe enthält nur yYnNjJ ? " + (Pattern.matches("[YNJ]*", eingabe.toUpperCase())));
        }while (!Pattern.matches("[YNJ]*", eingabe.toUpperCase()));
        System.out.println("so isses brav :-)");

    }
}
