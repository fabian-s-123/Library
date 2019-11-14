package controller;

import daos.*;
import entities.BookAuthorCategory;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.Scanner;

public class RecordNewUpdateDelete {

    public void createNewRecordBook(BookDAO boDAO) {
        System.out.println("Erfassung der notwendigen Daten für einen neuen Eintrag in book"); //TODO mögliche Fehleingaben bei der Erfassung abfangen, wenn mal Zeit ist
        System.out.println("Stelle sicher, dass die Autoren- und die Kategorien-ID für das zu erfassende Buch bekannt ist, bei entsprechenden Zeitressourcen wird hier weitergearbeitet.");
        Scanner sc_String = new Scanner(System.in);
        Scanner sc_int = new Scanner(System.in);
        System.out.print("Titel:              ");
        String title = sc_String.nextLine();
        System.out.print("Autoren-ID:         ");
        int idAuthor = sc_int.nextInt();
        System.out.print("Kategorien_ID:      ");
        int idCategory = sc_int.nextInt();
        System.out.print("ISBN:               ");
        long isbn = sc_int.nextLong();
        System.out.print("FSK/empfohlen ab:   ");
        int fsk = sc_int.nextInt();
        System.out.print("Verlag:             ");
        String publisher = sc_String.nextLine();
        System.out.print("aktuelle Ausgabe:   ");
        String edition = sc_String.nextLine();
        System.out.print("Erstausgabe:        ");
        String firstEdition = sc_String.nextLine();
        System.out.print("Anzahl der Seiten:  ");
        int amountPages = sc_int.nextInt();
        System.out.print("Sprache:            ");
        String language = sc_String.nextLine();
        System.out.print("Lagerort - ROW:     ");
        int idRow = sc_int.nextInt();
        System.out.print("           COLUMN:  ");
        int idColumn = sc_int.nextInt();
        boDAO.createRecordBook(title, idAuthor, idCategory, isbn, fsk, publisher, edition, firstEdition, amountPages, language, idRow, idColumn);
    }

    public void createNewRecordAuthor(AuthorDAO auDAO) {
        System.out.println("Erfassung der notwendigen Daten für einen neuen Eintrag in author"); //TODO mögliche Fehleingaben bei der Erfassung abfangen, wenn mal Zeit ist
        Scanner sc_String = new Scanner(System.in);
        Scanner sc_int = new Scanner(System.in);
        System.out.print("Vorname:           ");
        String firstName = sc_String.nextLine();
        System.out.print("Nachname:          ");
        String lastName = sc_String.nextLine();
        System.out.print("GeburtsJAHR:       ");
        int gebJahr = sc_int.nextInt();
        auDAO.createRecordAuthor(firstName, lastName, gebJahr);
    }

    public void createNewRecordCategory(CategoryDAO caDAO) {
        System.out.println("Erfassung der notwendigen Daten für einen neuen Eintrag in category"); //TODO mögliche Fehleingaben bei der Erfassung abfangen, wenn mal Zeit ist
        Scanner sc_String = new Scanner(System.in);
        System.out.print("Beschreibung der Kategorie: ");
        String description = sc_String.nextLine();
        caDAO.createRecordCategory(description);
    }

    public void createNewRecordCustomer(CustomerDAO cuDAO) {
        System.out.println("Erfassung der notwendigen Daten für einen neuen Eintrag in customer"); //TODO mögliche Fehleingaben bei der Erfassung abfangen, wenn mal Zeit ist
        System.out.println("Aus Gründen der Zeitersparnis erfolgt hier (noch) keine Validitätsprüfung bei der Erfassung der zahlreichen Eigenschaften des Kunden:");
        Scanner sc_String = new Scanner(System.in);
        Scanner sc_int = new Scanner(System.in);
        System.out.print("Vorname:           ");
        String firstName = sc_String.nextLine();
        System.out.print("Nachname:          ");
        String lastName = sc_String.nextLine();
        System.out.print("Straße:            ");
        String street = sc_String.nextLine();
        System.out.print("Hausnummer:        ");
        String apNr = sc_String.nextLine();
        System.out.print("PLZ:               ");
        int zip = sc_int.nextInt();
        System.out.print("Stadt:             ");
        String city = sc_String.nextLine();
        System.out.print("GeburtsTAG:        ");
        int gebTag = sc_int.nextInt();
        System.out.print("GeburtsMONAT:      ");
        int gebMonat = sc_int.nextInt();
        System.out.print("GeburtsJAHR:       ");
        int gebJahr = sc_int.nextInt();
        System.out.print("PIN-Code:          ");
        String pinCode = sc_String.nextLine();
        System.out.print("Email:             ");
        String email = sc_String.nextLine();
        System.out.print("KreditKartenNr:    ");
        long creditCardNr = sc_int.nextLong();
        System.out.print("CVC:               ");
        int cvc = sc_int.nextInt();
        System.out.print("gültig bis Jahr:   ");
        int expiryDateYear = sc_int.nextInt();
        System.out.print("       bis Monat:  ");
        int expiryDateMonth = sc_int.nextInt();
        cuDAO.createRecordCustomer(pinCode, email, firstName, lastName, LocalDateTime.of(gebJahr, gebMonat, gebTag, 0, 0), street, apNr, zip, city, creditCardNr, cvc, expiryDateYear, expiryDateMonth);
    }

    public void createNewRecordLoaned(LoanedDAO loDAO) {
        System.out.println("Erfassung der notwendigen Daten für einen neuen Eintrag in loaned"); //TODO mögliche Fehleingaben bei der Erfassung abfangen, wenn mal Zeit ist
        System.out.println("Stelle sicher, dass die Buch-/Book- und die Kunden-/Customer-ID für den zu erfassenden Leihvorgang bekannt ist, bei entsprechenden Zeitressourcen wird hier weitergearbeitet.");
        Scanner sc_String = new Scanner(System.in);
        Scanner sc_int = new Scanner(System.in);
        System.out.print("ID Kunde/customer:   ");
        int idCustomer = sc_int.nextInt();
        System.out.print("ID Buch/book:        ");
        int idBook = sc_int.nextInt();
        System.out.print("Ausleih-TAG:         ");
        int leihTag = sc_int.nextInt();
        System.out.print("Ausleih-MONAT:       ");
        int leihMonat = sc_int.nextInt();
        System.out.print("Ausleih-JAHR:        ");
        int leihJahr = sc_int.nextInt();
        loDAO.createRecordLoanedWithoutReturn(idCustomer, idBook, LocalDateTime.of(leihJahr, leihMonat, leihTag, 0, 0));
    }


    public void deleteRecordBook(BookDAO boDAO, DiverseLists diLi) {
        LinkedList<BookAuthorCategory> listAllBooks = diLi.createListeBookAllRecords(boDAO);
        Scanner sc_int = new Scanner(System.in);

        boolean eingabegueltig = true;
        do {
            eingabegueltig = true;
            System.out.print("Welche laufende Nr. aus der vorstehenden Liste soll gelöscht werden? ");
            int zuLoeschendeNr = sc_int.nextInt();
            if (zuLoeschendeNr < 1 || zuLoeschendeNr > listAllBooks.size()) {
                System.out.println("Die Eingabe war ungültig, bitte wiederholen! ");
                eingabegueltig = false;
            }
        } while (!eingabegueltig);
    }
    // hier geht es dann weiter - aus der Liste die ID des zu löschenden Buches heraussuchen und dann in Tabelle löschen

    public void deleteRecordAuthor(AuthorDAO auDAO) {
    }

    public void deleteRecordCategory(CategoryDAO caDAO) {
    }

    public void deleteRecordCustomer(CustomerDAO cuDAO) {
    }

    public void deleteRecordLoaned(LoanedDAO loDAO) {
    }
}
