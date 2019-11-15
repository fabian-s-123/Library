package controller;

import daos.*;
import entities.*;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.Scanner;

public class RecordNewUpdateDelete {

    public void createNewRecordBook(BookDAO boDAO, boolean isCreated) {
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

    public void createNewRecordAuthor(AuthorDAO auDAO, boolean isCreated) {
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

    public void createNewRecordCategory(CategoryDAO caDAO, boolean isCreated, int idCategory) {
        System.out.println("Erfassung der notwendigen Daten für einen neuen Eintrag in category"); //TODO mögliche Fehleingaben bei der Erfassung abfangen, wenn mal Zeit ist
        Scanner sc_String = new Scanner(System.in);
        System.out.print("Beschreibung der Kategorie: ");
        String description = sc_String.nextLine();
        if (isCreated) {
            caDAO.createRecordCategory(description);
        } else {
            //caDAO.editRecordCategory()
        }
    }

    public void createNewRecordCustomer(CustomerDAO cuDAO, boolean isCreated) {
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

    public void createNewRecordLoaned(LoanedDAO loDAO, boolean isCreated) {
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

    public void editRecordBook(BookDAO boDAO, DiverseLists diLi) {//fehlt noch
    }

    public void editRecordAuthor(AuthorDAO auDAO, DiverseLists diLi) {//fehlt noch
    }

    public void editRecordCategory(CategoryDAO caDAO, DiverseLists diLi) {
        diLi.createListeCategoryAllRecords(caDAO);
        Scanner sc_int = new Scanner(System.in);
        int zuEditierendeIdCategory = 0;
        boolean eingabegueltig = true;
        do {
            eingabegueltig = true;
            System.out.print("Bitte gib die IdKategorie von dem zu editierenden Eintrag aus der vorstehenden Liste ein! ");
            zuEditierendeIdCategory = sc_int.nextInt();
            boolean categoryIsInTable = caDAO.checkIsIDCategoryInTable(zuEditierendeIdCategory);
            if (!categoryIsInTable) {
                System.out.println("Die Eingabe war ungültig, bitte wiederholen! ");
                eingabegueltig = false;
            }
        } while (!eingabegueltig);
        System.out.println("Gib die neuen Werte ein:");
        createNewRecordCategory(caDAO, false, zuEditierendeIdCategory);
        //hier gehts weiter

    }

    public void editRecordCustomer(CustomerDAO cuDAO, DiverseLists diLi) {//fehlt noch
    }

    public void editRecordLoaned(LoanedDAO loDAO, DiverseLists diLi) {//fehlt noch
    }

    public void deleteRecordBook(BookDAO boDAO, DiverseLists diLi) {
        diLi.createListeBookAllRecords(boDAO);
        Scanner sc_int = new Scanner(System.in);
        int zuLoeschendeIdBook = 0;
        boolean eingabegueltig = true;
        do {
            eingabegueltig = true;
            System.out.print("Bitte gib die IdBook vom zu löschenden Buch aus der vorstehenden Liste ein! ");
            zuLoeschendeIdBook = sc_int.nextInt();
            boolean bookIsInTable = boDAO.checkIsIDBookInTable(zuLoeschendeIdBook);
            if (!bookIsInTable) {
                System.out.println("Die Eingabe war ungültig, bitte wiederholen! ");
                eingabegueltig = false;
            }
        } while (!eingabegueltig);
        String query = "DELETE FROM book WHERE IdBook = " + zuLoeschendeIdBook;
        boDAO.executeStatement(query, "Ein Datensatz aus book wurde gelöscht.");
    }

    public void deleteRecordAuthor(AuthorDAO auDAO, DiverseLists diLi) {
        diLi.createListeAuthorAllRecords(auDAO);
        Scanner sc_int = new Scanner(System.in);
        int zuLoeschendeIdAuthor = 0;
        boolean eingabegueltig = true;
        do {
            eingabegueltig = true;
            System.out.print("Bitte gib die IdAuthor vom zu löschenden Author aus der vorstehenden Liste ein! ");
            zuLoeschendeIdAuthor = sc_int.nextInt();
            boolean authorIsInTable = auDAO.checkIsIDAuthorInTable(zuLoeschendeIdAuthor);
            if (!authorIsInTable) {
                System.out.println("Die Eingabe war ungültig, bitte wiederholen! ");
                eingabegueltig = false;
            }
        } while (!eingabegueltig);
        String query = "DELETE FROM book WHERE IdBook = " + zuLoeschendeIdAuthor;
        auDAO.executeStatement(query, "Ein Datensatz aus author wurde gelöscht.");
    }

    public void deleteRecordCategory(CategoryDAO caDAO, DiverseLists diLi) {
        diLi.createListeCategoryAllRecords(caDAO);
        Scanner sc_int = new Scanner(System.in);
        int zuLoeschendeIdCategory = 0;
        boolean eingabegueltig = true;
        do {
            eingabegueltig = true;
            System.out.print("Bitte gib die IdKategorie von der zu löschenden Kategorie aus der vorstehenden Liste ein! ");
            zuLoeschendeIdCategory = sc_int.nextInt();
            boolean categoryIsInTable = caDAO.checkIsIDCategoryInTable(zuLoeschendeIdCategory);
            if (!categoryIsInTable) {
                System.out.println("Die Eingabe war ungültig, bitte wiederholen! ");
                eingabegueltig = false;
            }
        } while (!eingabegueltig);
        String query = "DELETE FROM category WHERE IdCategory = " + zuLoeschendeIdCategory;
        caDAO.executeStatement(query, "Ein Datensatz aus category wurde gelöscht.");
    }

    public void deleteRecordCustomer(CustomerDAO cuDAO, DiverseLists diLi) {
        diLi.createListeCustomerAllRecords(cuDAO);
        Scanner sc_int = new Scanner(System.in);
        int zuLoeschendeIdCustomer = 0;
        boolean eingabegueltig = true;
        do {
            eingabegueltig = true;
            System.out.print("Bitte gib die IdCustomer vom zu löschenden Kunden/Customer aus der vorstehenden Liste ein! ");
            zuLoeschendeIdCustomer = sc_int.nextInt();
            boolean customerIsInTable = cuDAO.checkIsIDCustomerInTable(zuLoeschendeIdCustomer);
            if (!customerIsInTable) {
                System.out.println("Die Eingabe war ungültig, bitte wiederholen! ");
                eingabegueltig = false;
            }
        } while (!eingabegueltig);
        String query = "DELETE FROM customer WHERE IdCustomer = " + zuLoeschendeIdCustomer;
        cuDAO.executeStatement(query, "Ein Datensatz aus customer wurde gelöscht.");
    }

    public void deleteRecordLoaned(LoanedDAO loDAO, DiverseLists diLi) {
        diLi.createListeLoanedAllRecords(loDAO);
        Scanner sc_int = new Scanner(System.in);
        int zuLoeschendeIdLoaned = 0;
        boolean eingabegueltig = true;
        do {
            eingabegueltig = true;
            System.out.print("Bitte gib die IdLoaned vom zu löschenden Leihvorgang aus der vorstehenden Liste ein! ");
            zuLoeschendeIdLoaned = sc_int.nextInt();
            boolean loanedIsInTable = loDAO.checkIsIDLoanedInTable(zuLoeschendeIdLoaned);
            if (!loanedIsInTable) {
                System.out.println("Die Eingabe war ungültig, bitte wiederholen! ");
                eingabegueltig = false;
            }
        } while (!eingabegueltig);
        String query = "DELETE FROM loaned WHERE IdLoaned = " + zuLoeschendeIdLoaned;
        loDAO.executeStatement(query, "Ein Datensatz aus loaned wurde gelöscht.");
    }
}
