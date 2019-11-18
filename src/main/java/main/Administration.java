package main;

import controller.DiverseLists;
import controller.RecordNewUpdateDelete;
import daos.*;

import java.util.Scanner;

// was könnte ich noch tun: bei eingabe Buch prüfen, ob idAuthor und idCategory vorhanden sind
// Optimierungshinweise von intelliJ anschauen

public class Administration {

    public static void main(String[] args) {
        System.out.println("Willkommen in der Administration");
        String[] auswahlString1 = {"Book", "Author", "Category", "Customer", "Loaned", "diverse Listen"};
        String[] auswahlString2 = {"Wer hat welche Bücher derzeit ausgeliehen", "Wer hat welche Bücher überhaupt ausgeliehen", "Welche Bücher sind derzeit ausgeliehen", "Welche Bücher sind derzeit im Haus", "Welche Bücher wurden überhaupt verliehen", "Welche Bücher wurden gar nicht verliehen", "Vom Autor ... sind welche Bücher gelistet", "In welcher Kategorie sind welche Bücher gelistet", "In welcher Sprache sind welche Bücher gelistet"};
        DBConnector dbConnector;
        dbConnector = DBConnector.getInstance();
        dbConnector.connect("w0136ee0.kasserver.com", "d03037fa", "d03037fa", "fpcQdPhv5v4UoQ6H");

        CustomerDAO cuDAO = new CustomerDAO();
        BookDAO boDAO = new BookDAO();
        AuthorDAO auDAO = new AuthorDAO();
        CategoryDAO caDAO = new CategoryDAO();
        LoanedDAO loDAO = new LoanedDAO();
        DiverseLists diLi = new DiverseLists();
        RecordNewUpdateDelete recNUD = new RecordNewUpdateDelete();

        boolean verbleibImProgramm = true;
        boolean verbleibInAuswahlstufe;
        int auswahl1; //für 1. Menüstufe (Pflege Book, Author, Category, Customer, Loaned, Übersichten
        int auswahl2; //für 2. Menüstufe (Liste, anfügen, ändern, löschen)
        int auswahl3; //für 2. Menüstufe (diverse Listen)
        Scanner sc = new Scanner(System.in);
        do { //Verbleib im Programm
            boolean isEingabeGueltig = false;
            do {
                System.out.println("Bitte wähle aus - 0 = Programmende");
                for (int i = 0; i < auswahlString1.length - 1; i++) {
                    System.out.println("                  " + (i + 1) + " = Pflege Daten " + auswahlString1[i]);
                }
                System.out.println("                  6 = diverse Übersichten");
                auswahl1 = sc.nextInt();
                if (auswahl1 >= 0 && auswahl1 < 7) {
                    isEingabeGueltig = true;
                } else {
                    System.out.println("Deine Eingabe war ungültig, bitte wiederhole die Auswahl!");
                }
            } while (!isEingabeGueltig);

            if (auswahl1 == 0) {
                verbleibImProgramm = false;
            } else if (auswahl1 == 6) {
                do { //Verbleib in Auswahlstufe
                    verbleibInAuswahlstufe = true;
                    isEingabeGueltig = false;
                    do {
                        System.out.println("Was möchtest du im Bereich " + auswahlString1[auswahl1 - 1].toUpperCase() + " erledigen?");
                        System.out.println("Bitte wähle aus - 0 = zurück zur vorherigen Auswahl ");
                        for (int i = 0; i < auswahlString2.length; i++) {
                            System.out.println("                  " + (i + 1) + " = " + auswahlString2[i]);
                        }
                        auswahl3 = sc.nextInt();
                        if (auswahl3 >= 0 && auswahl3 <= auswahlString2.length) {
                            isEingabeGueltig = true;
                        } else {
                            System.out.println("Deine Eingabe war ungültig, bitte wiederhole die Auswahl!");
                        }
                    } while (!isEingabeGueltig);
                    switch (auswahl3) {
                        case 0:
                            System.out.println("Dein Wunsch ist mein Befehl - wir gehen zurück zur vorherigen Auswahl");
                            verbleibInAuswahlstufe = false;
                            break;
                        case 1:
                            System.out.println("Hier ist die Liste zu (" + auswahlString1[auswahl1 - 1].toUpperCase() + " - " + auswahlString2[auswahl3 - 1] + ").");
                            diLi.createListWerHatDerzeitWelcheBuecherAusgeliehen(loDAO, auswahlString2[auswahl3 - 1]);
                            break;
                        case 2:
                            System.out.println("Hier ist die Liste zu (" + auswahlString1[auswahl1 - 1].toUpperCase() + " - " + auswahlString2[auswahl3 - 1] + ").");
                            diLi.createListWerHatUeberhauptBuecherAusgeliehen(loDAO, auswahlString2[auswahl3 - 1]);
                            break;
                        case 3:
                            System.out.println("Hier ist die Liste zu  (" + auswahlString1[auswahl1 - 1].toUpperCase() + " - " + auswahlString2[auswahl3 - 1] + ").");
                            diLi.createListWelcheBuechersindderzeitausgeliehen(loDAO, auswahlString2[auswahl3 - 1]);
                            break;
                        case 4:
                            System.out.println("Hier ist die Liste zu  (" + auswahlString1[auswahl1 - 1].toUpperCase() + " - " + auswahlString2[auswahl3 - 1] + ").");
                            diLi.createListWelcheBuechersindderzeitimHaus(boDAO, loDAO, auswahlString2[auswahl3 - 1]);
                            break;
                        case 5:
                            System.out.println("Hier ist die Liste zu  (" + auswahlString1[auswahl1 - 1].toUpperCase() + " - " + auswahlString2[auswahl3 - 1] + ").");
                            diLi.createListWelcheBuecherwurdenueberhauptverliehen(loDAO, auswahlString2[auswahl3 - 1]);
                            break;
                        case 6:
                            System.out.println("Hier ist die Liste zu  (" + auswahlString1[auswahl1 - 1].toUpperCase() + " - " + auswahlString2[auswahl3 - 1] + ").");
                            diLi.createListeWelcheBuecherwurdengarnichtverliehen(boDAO, loDAO, auswahlString2[auswahl3 - 1]);
                            break;
                        case 7:
                            System.out.println("Hier ist die Liste zu  (" + auswahlString1[auswahl1 - 1].toUpperCase() + " - " + auswahlString2[auswahl3 - 1] + ").");
                            diLi.createListeVomAutorsindwelcheBuechergelistet(boDAO, auswahlString2[auswahl3 - 1]);
                            break;
                        case 8:
                            System.out.println("Hier ist die Liste zu  (" + auswahlString1[auswahl1 - 1].toUpperCase() + " - " + auswahlString2[auswahl3 - 1] + ").");
                            diLi.createListeInwelcherKategoriesindwelcheBuechergelistet(boDAO, auswahlString2[auswahl3 - 1]);
                            break;
                        case 9:
                            System.out.println("Hier ist die Liste zu  (" + auswahlString1[auswahl1 - 1].toUpperCase() + " - " + auswahlString2[auswahl3 - 1] + ").");
                            diLi.createListeInwelcherSprachesindwelcheBuechergelistet(boDAO, auswahlString2[auswahl3 - 1]);
                            break;
                    }
                } while (verbleibInAuswahlstufe);
            } else {
                do { //Verbleib in Auswahlstufe
                    verbleibInAuswahlstufe = true;
                    isEingabeGueltig = false;
                    do {
                        System.out.println("Was möchtest du im Bereich " + auswahlString1[auswahl1 - 1].toUpperCase() + " erledigen?");
                        System.out.println("Bitte wähle aus - 0 = zurück zur vorherigen Auswahl ");
                        System.out.println("                  1 = Liste vorhandener Einträge");
                        System.out.println("                  2 = neuen Datensatz erstellen");
                        System.out.println("                  3 = einen Datensatz editieren");
                        System.out.println("                  4 = einen Datensatz löschen");
                        auswahl2 = sc.nextInt();
                        if (auswahl2 >= 0 && auswahl2 < 5) {
                            isEingabeGueltig = true;
                        } else {
                            System.out.println("Deine Eingabe war ungültig, bitte wiederhole die Auswahl!");
                        }
                    } while (!isEingabeGueltig);
                    switch (auswahl2) {
                        case 0:
                            System.out.println("Dein Wunsch ist mein Befehl - wir gehen zurück zur vorherigen Auswahl");
                            verbleibInAuswahlstufe = false;
                            break;
                        case 1:
                            System.out.println("Hier ist die Liste aller DS zu (" + auswahlString1[auswahl1 - 1].toUpperCase() + ").");
                            switch (auswahl1) {
                                case 1: //book
                                    diLi.createListeBookAllRecords(boDAO);
                                    break;
                                case 2: //author
                                    diLi.createListeAuthorAllRecords(auDAO);
                                    break;
                                case 3: //category
                                    diLi.createListeCategoryAllRecords(caDAO);
                                    break;
                                case 4: //customer
                                    diLi.createListeCustomerAllRecords(cuDAO);
                                    break;
                                case 5: //loaned
                                    diLi.createListeLoanedAllRecords(loDAO);
                                    break;
                            }
                            break;
                        case 2:
                            System.out.println("Hier geht es zum Erstellen eines neuen DS in (" + auswahlString1[auswahl1 - 1].toUpperCase() + ").");
                            switch (auswahl1) {
                                case 1: //book
                                    recNUD.createNewRecordBook(boDAO);
                                    break;
                                case 2: //author
                                    recNUD.createNewRecordAuthor(auDAO);
                                    break;
                                case 3: //category
                                    recNUD.createNewRecordCategory(caDAO);
                                    break;
                                case 4: //customer
                                    recNUD.createNewRecordCustomer(cuDAO);
                                    break;
                                case 5: //loaned
                                    recNUD.createNewRecordLoaned(loDAO);
                                    break;
                            }
                            break;
                        case 3:
                            System.out.println("Hier geht es zum Editieren eines einzelnen DS in (" + auswahlString1[auswahl1 - 1].toUpperCase() + ").");
                            switch (auswahl1) {
                                case 1: //book
                                    recNUD.editRecordBook(boDAO, diLi);
                                    break;
                                case 2: //author
                                    recNUD.editRecordAuthor(auDAO, diLi);
                                    break;
                                case 3: //category
                                    recNUD.editRecordCategory(caDAO, diLi);
                                    break;
                                case 4: //customer
                                    recNUD.editRecordCustomer(cuDAO, diLi);
                                    break;
                                case 5: //loaned
                                    recNUD.editRecordLoaned(loDAO, diLi);
                                    break;
                            }
                            break;
                        case 4:
                            System.out.println("Hier geht es zum Löschen eines einzelnen DS in (" + auswahlString1[auswahl1 - 1].toUpperCase() + ").");
                            switch (auswahl1) {
                                case 1: //book
                                    recNUD.deleteRecordBook(boDAO, diLi);
                                    break;
                                case 2: //author
                                    recNUD.deleteRecordAuthor(auDAO, diLi);
                                    break;
                                case 3: //category
                                    recNUD.deleteRecordCategory(caDAO, diLi);
                                    break;
                                case 4: //customer
                                    recNUD.deleteRecordCustomer(cuDAO, diLi);
                                    break;
                                case 5: //loaned
                                    recNUD.deleteRecordLoaned(loDAO, diLi);
                                    break;
                            }
                            break;
                    }
                } while (verbleibInAuswahlstufe);
            }//else
        } while (verbleibImProgramm);
        dbConnector.close();
        System.out.println("Reguläres Programmende");
    }
}