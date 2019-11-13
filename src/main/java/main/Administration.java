package main;

import java.util.Scanner;

public class Administration {

    public static void main(String[] args) {
        System.out.println("Willkommen in der Administration");
        String[] auswahlString1 = {"Book", "Author", "Category", "Customer", "Loaned", "diverse Listen"};
        boolean verbleibImProgramm = true;
        boolean verbleibInAuswahlstufe = true;
        int auswahl1 = 0; //für 1. Menüstufe (Pflege Book, Author, Category, Customer, Loaned, Übersichten
        int auswahl2 = 0; //für 2. Menüstufe (Liste, anfügen, ändern, löschen)
        int auswahl3 = 0; //für 2. Menüstufe (diverse Listen)
        Scanner sc = new Scanner(System.in);
        do { //Verbleib im Programm
            boolean isEingabeGueltig = false;
            do {
                System.out.println("Bitte wähle aus - 0 = Programmende");
                System.out.println("                  1 = Pflege Daten Book");
                System.out.println("                  2 = Pflege Daten Author");
                System.out.println("                  3 = Pflege Daten Category");
                System.out.println("                  4 = Pflege Daten Customer");
                System.out.println("                  5 = Pflege Daten Loaned ");
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

            } else if(auswahl1 == 6) {
                do { //Verbleib in Auswahlstufe
                    verbleibInAuswahlstufe = true;
                    isEingabeGueltig = false;
                    do {
                        System.out.println("Was möchtest du im Bereich " + auswahlString1[auswahl1 - 1].toUpperCase() + " erledigen?");
                        System.out.println("Bitte wähle aus - 0 = zurück zur vorherigen Auswahl ");
                        System.out.println("                  1 = Wer hat welche Bücher derzeit ausgeliehen");
                        System.out.println("                  2 = Wer hat welche Bücher überhaupt ausgeliehen");
                        System.out.println("                  3 = Welche Bücher sind derzeit ausgeliehen");
                        System.out.println("                  4 = Welche Bücher sind wurden überhaupt verliehen");
                        System.out.println("                  5 = Welche Bücher wurden gar nicht verliehen");
                        System.out.println("                  6 = Vom Autor ... sind welche Bücher gelistet");
                        System.out.println("                  7 = In welcher Kategorie sind welche Bücher");
                        auswahl3 = sc.nextInt();
                        if (auswahl3 >= 0 && auswahl3 < 8) {
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
                            System.out.println("Hier ist die Liste 1 zu ("  + auswahlString1[auswahl1 - 1].toUpperCase() + ") vorgesehen");
                            break;
                        case 2:
                            System.out.println("Hier ist die Liste 2 zu ("  + auswahlString1[auswahl1 - 1].toUpperCase() + ") vorgesehen");
                            break;
                        case 3:
                            System.out.println("Hier ist die Liste 3 zu  ("  + auswahlString1[auswahl1 - 1].toUpperCase() + ") vorgesehen");
                            break;
                        case 4:
                            System.out.println("Hier ist die Liste 4 zu  ("  + auswahlString1[auswahl1 - 1].toUpperCase() + ")vorgesehen");
                            break;
                        case 5:
                            System.out.println("Hier ist die Liste 5 zu  ("  + auswahlString1[auswahl1 - 1].toUpperCase() + ")vorgesehen");
                            break;
                        case 6:
                            System.out.println("Hier ist die Liste 6 zu  ("  + auswahlString1[auswahl1 - 1].toUpperCase() + ")vorgesehen");
                            break;
                        case 7:
                            System.out.println("Hier ist die Liste 7 zu  ("  + auswahlString1[auswahl1 - 1].toUpperCase() + ")vorgesehen");
                            break;
                    }
                } while (verbleibInAuswahlstufe);
            } else
                {
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
                            System.out.println("Deine Eingabe war ungültig, bitte wiederhole deine Auswahl!");
                        }
                    } while (!isEingabeGueltig);
                    switch (auswahl2) {
                        case 0:
                            System.out.println("Dein Wunsch ist mein Befehl - wir gehen zurück zur vorherigen Auswahl");
                            verbleibInAuswahlstufe = false;
                            break;
                        case 1:
                            System.out.println("Hier ist die Liste aller DS zu ("  + auswahlString1[auswahl1 - 1].toUpperCase() + ") vorgesehen");
                            break;
                        case 2:
                            System.out.println("Hier ist das Erstellen eines neuen DS in ("  + auswahlString1[auswahl1 - 1].toUpperCase() + ") vorgesehen");
                            break;
                        case 3:
                            System.out.println("Hier ist das Editieren eines einzelnen DS in ("  + auswahlString1[auswahl1 - 1].toUpperCase() + ") vorgesehen");
                            break;
                        case 4:
                            System.out.println("Hier ist das Löschen eines einzelnen DS in ("  + auswahlString1[auswahl1 - 1].toUpperCase() + ")vorgesehen");
                            break;
                    }
                } while (verbleibInAuswahlstufe);
            }//else
        } while (verbleibImProgramm);
        System.out.println("Reguläres Programmende");
    }
}