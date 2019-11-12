import java.util.Scanner;

public class Administration {

    public static void main(String[] args) {
        System.out.println("Willkommen in der Administration");
        String[] auswahlString1 = {"Book", "Author", "Category", "Customer", "Loaned"};
        boolean verbleibImProgramm = true;
        boolean verbleibInAuswahlstufe = true;
        int auswahl1 = 0;
        int auswahl2 = 0;
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
                auswahl1 = sc.nextInt();
                if (auswahl1 >= 0 && auswahl1 < 6) {
                    isEingabeGueltig = true;
                } else {
                    System.out.println("Deine Eingabe war ungültig, bitte wiederhole die Auswahl!");
                }
            } while (!isEingabeGueltig);
            if (auswahl1 == 0) {
                verbleibImProgramm = false;
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


