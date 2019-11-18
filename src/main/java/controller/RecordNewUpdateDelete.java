package controller;

import daos.*;
import entities.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Scanner;

public class RecordNewUpdateDelete {

    private Book inputDataBook(AuthorDAO auDAO, CategoryDAO caDAO, DiverseLists diLi) {
        System.out.println("Stelle sicher, dass die Autoren- und die Kategorien-ID für das zu erfassende Buch bekannt ist, bei entsprechenden Zeitressourcen wird hier weitergearbeitet.");
        Scanner sc_String = new Scanner(System.in);
        Scanner sc_int = new Scanner(System.in);
        System.out.print("Titel:              ");
        String title = sc_String.nextLine();
        System.out.print("Autoren-ID:         ");
        int idAuthor = inputIdAuthor(auDAO, diLi, "auszuwählenden");
        if (idAuthor == 0) idAuthor = 1;
        System.out.print("Kategorien_ID:      ");
        int idCategory = inputIdCategory(caDAO, diLi, "auszuwählenden");
        if (idCategory == 0) idCategory = 1;
        System.out.print("ISBN:               ");
        long isbn = sc_int.nextLong();
        if (isbn < 0) isbn = isbn * -1;
        if (isbn > 9999999999999L) isbn = 9999999999999L;
        System.out.print("FSK/empfohlen ab:   ");
        int fsk = sc_int.nextInt();
        if (isbn < 0) fsk = fsk * -1;
        if (fsk > 99) fsk = 99;
        System.out.print("Verlag:             ");
        String publisher = sc_String.nextLine();
        System.out.print("aktuelle Ausgabe:   ");
        String edition = sc_String.nextLine();
        System.out.print("Erstausgabe:        ");
        String firstEdition = sc_String.nextLine();
        System.out.print("Anzahl der Seiten:  ");
        int amountPages = sc_int.nextInt();
        if (amountPages < 0) amountPages = amountPages * -1;
        if (amountPages > 999) amountPages = 999;
        System.out.print("Sprache:            ");
        String language = sc_String.nextLine();
        System.out.print("Lagerort - ROW:     ");
        int idRow = sc_int.nextInt();
        if (idRow < 0) idRow = idRow * -1;
        if (idRow > 99) idRow = 99;
        System.out.print("           COLUMN:  ");
        int idColumn = sc_int.nextInt();
        if (idColumn < 0) idColumn = idColumn * -1;
        if (idColumn > 99) idColumn = 99;
        Book temp = new Book(0, title, idAuthor, idCategory, isbn, fsk, publisher, edition, firstEdition, amountPages, language, idRow, idColumn, null, null);
        return temp;
    }

    private int inputIdBook(BookDAO boDAO, DiverseLists diLi, String meldung) {
        diLi.createListeBookAllRecords(boDAO);
        Scanner sc_int = new Scanner(System.in);
        int idBook;
        boolean eingabegueltig;
        do {
            eingabegueltig = true;
            System.out.print("Bitte gib die IdBook vom zu " + meldung + " Buch aus der vorstehenden Liste ein! ");
            idBook = sc_int.nextInt();
            if (idBook == 0) {
                System.out.println("Zurück zur Auswahl ohne Auswahl des zu " + meldung + " Buch.");
            } else {
                boolean bookIsInTable = boDAO.checkIsXxxIdInTableXxx("idBook", "book", idBook);
                if (!bookIsInTable) {
                    System.out.println("Die Eingabe war ungültig, bitte wiederholen! ");
                    eingabegueltig = false;
                }
            }
        } while (!eingabegueltig);
        return idBook;
    }

    public void createNewRecordBook(BookDAO boDAO, AuthorDAO auDAO, CategoryDAO caDAO, DiverseLists diLi) {
        System.out.println("Erfassung der notwendigen Daten für einen neuen Eintrag in book");
        Book temp = inputDataBook(auDAO, caDAO, diLi);
        boDAO.createRecordBook(temp.getTitle(), temp.getIdAuthor(), temp.getIdCategory(), temp.getIsbn(), temp.getFsk(),
                temp.getPublisher(), temp.getEdition(), temp.getFirstEdition(), temp.getAmountPages(), temp.getLanguage(),
                temp.getIdRow(), temp.getIdColumn());
    }

    public void editRecordBook(BookDAO boDAO, AuthorDAO auDAO, CategoryDAO caDAO, DiverseLists diLi) {
        int zuEditierendeIdBook = inputIdBook(boDAO, diLi, "editierenden");
        if (zuEditierendeIdBook > 0) {
            Book temp = inputDataBook(auDAO, caDAO, diLi);
            boDAO.updateRecordBook(temp.getTitle(), temp.getIdAuthor(), temp.getIdCategory(), temp.getIsbn(), temp.getFsk(),
                    temp.getPublisher(), temp.getEdition(), temp.getFirstEdition(), temp.getAmountPages(), temp.getLanguage(),
                    temp.getIdRow(), temp.getIdColumn(), zuEditierendeIdBook);
        }
    }

    public void deleteRecordBook(BookDAO boDAO, DiverseLists diLi) {
        int zuLoeschendeIdBook = inputIdBook(boDAO, diLi, "löschenden");
        if (zuLoeschendeIdBook > 0) {
            boolean isBookInLoaned = boDAO.checkIsXxxIdInTableXxx("idBook", "loaned", zuLoeschendeIdBook);
            if (isBookInLoaned) {
                System.out.println("Von diesem Buch gibt es Einträge in Loaned, deswegen ist ein Löschen des Buchs nicht möglich.");
            } else {
                boDAO.createQueryDeleteID("book", "IdBook", zuLoeschendeIdBook);
            }
        }
    }

    private Author inputDataAuthor() {
        Scanner sc_String = new Scanner(System.in);
        Scanner sc_int = new Scanner(System.in);
        System.out.print("Vorname:           ");
        String firstName = sc_String.nextLine();
        System.out.print("Nachname:          ");
        String lastName = sc_String.nextLine();
        System.out.print("GeburtsJAHR:       ");
        int gebJahr = sc_int.nextInt();
        if (gebJahr<0) gebJahr=gebJahr*-1;
        if (gebJahr > 9999) gebJahr = 9999;
        Author temp = new Author(0, firstName, lastName, gebJahr, null, null);
        return temp;
    }

    private int inputIdAuthor(AuthorDAO auDAO, DiverseLists diLi, String meldung) {
        diLi.createListeAuthorAllRecords(auDAO);
        Scanner sc_int = new Scanner(System.in);
        int zuLoeschendeIdAuthor;
        boolean eingabegueltig;
        do {
            eingabegueltig = true;
            System.out.print("Bitte gib die IdAuthor vom zu " + meldung + " Author aus der vorstehenden Liste ein! ");
            zuLoeschendeIdAuthor = sc_int.nextInt();
            if (zuLoeschendeIdAuthor == 0) {
                System.out.println("Zurück zur Auswahl ohne Auswahl des zu " + meldung + " Autor.");
            } else {
                boolean authorIsInTable = auDAO.checkIsXxxIdInTableXxx("idAuthor", "author", zuLoeschendeIdAuthor);
                if (!authorIsInTable) {
                    System.out.println("Die Eingabe war ungültig, bitte wiederholen! ");
                    eingabegueltig = false;
                }
            }
        } while (!eingabegueltig);
        return zuLoeschendeIdAuthor;
    }

    public void createNewRecordAuthor(AuthorDAO auDAO) {
        System.out.println("Erfassung der notwendigen Daten für einen neuen Eintrag in author");
        Author temp = inputDataAuthor();
        auDAO.createRecordAuthor(temp.getFirstName(), temp.getLastName(), temp.getBirthYear());
    }

    public void editRecordAuthor(AuthorDAO auDAO, DiverseLists diLi) {//fehlt noch
        int zuEditierendeIdAuthor = inputIdAuthor(auDAO, diLi, "editierenden");
        if (zuEditierendeIdAuthor > 0) {
            Author temp = inputDataAuthor();
            auDAO.updateRecordAuthor(temp.getFirstName(), temp.getLastName(), temp.getBirthYear(), zuEditierendeIdAuthor);
        }
    }

    public void deleteRecordAuthor(AuthorDAO auDAO, DiverseLists diLi) {
        int zuLoeschendeIdAuthor = inputIdAuthor(auDAO, diLi, "löschenden");
        if (zuLoeschendeIdAuthor > 0) {
            boolean isAuthorInBook = auDAO.checkIsXxxIdInTableXxx("idAuthor", "book", zuLoeschendeIdAuthor);
            if (isAuthorInBook) {
                System.out.println("Von diesem Autor sind noch Bücher in der Liste book, deswegen ist ein Löschen des Autors nicht möglich.");
            } else {
                auDAO.createQueryDeleteID("author", "IdAuthor", zuLoeschendeIdAuthor);
            }
        }
    }

    private Category inputDataCategory() {
        Scanner sc_String = new Scanner(System.in);
        System.out.print("Beschreibung der Kategorie: ");
        String description = sc_String.nextLine();
        Category temp = new Category(0, description, null, null);
        return temp;
    }

    private int inputIdCategory(CategoryDAO caDAO, DiverseLists diLi, String meldung) {
        diLi.createListeCategoryAllRecords(caDAO);
        Scanner sc_int = new Scanner(System.in);
        int idCategory;
        boolean eingabegueltig;
        do {
            eingabegueltig = true;
            System.out.print("Bitte gib die IdKategorie von dem zu " + meldung + " Eintrag aus der vorstehenden Liste ein! ");
            idCategory = sc_int.nextInt();
            if (idCategory == 0) {
                System.out.println("Zurück zur Auswahl ohne Auswahl des zu " + meldung + " Kategorie.");
            } else {
                boolean categoryIsInTable = caDAO.checkIsXxxIdInTableXxx("idCategory", "category", idCategory);
                if (!categoryIsInTable) {
                    System.out.println("Die Eingabe war ungültig, bitte wiederholen! ");
                    eingabegueltig = false;
                }
            }
        }
        while (!eingabegueltig);
        return idCategory;
    }

    public void createNewRecordCategory(CategoryDAO caDAO) {
        System.out.println("Erfassung der notwendigen Daten für einen neuen Eintrag in category");
        Category temp = inputDataCategory();
        caDAO.createRecordCategory(temp.getDescription());
    }

    public void editRecordCategory(CategoryDAO caDAO, DiverseLists diLi) {
        int zuEditierendeIdCategory = inputIdCategory(caDAO, diLi, "editierenden");
        if (zuEditierendeIdCategory > 0) {
            Category temp = inputDataCategory();
            caDAO.updateRecordCategory(temp.getDescription(), zuEditierendeIdCategory);
        }
    }

    public void deleteRecordCategory(CategoryDAO caDAO, DiverseLists diLi) {
        int zuLoeschendeIdCategory = inputIdCategory(caDAO, diLi, "löschenden");
        if (zuLoeschendeIdCategory > 0) {
            boolean isCategoryInBook = caDAO.checkIsXxxIdInTableXxx("idCategory", "book", zuLoeschendeIdCategory);
            if (isCategoryInBook) {
                System.out.println("In dieser Kategorie sind noch Bücher in der Liste book, deswegen ist ein Löschen dieser Kategorie nicht möglich.");
            } else {
                caDAO.createQueryDeleteID("category", "idCategory", zuLoeschendeIdCategory);
            }
        }
    }

    private Customer inputDataCustomer() {
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
        if (apNr.length() > 7) apNr = apNr.substring(0, 7);
        System.out.print("PLZ:               ");
        int zip = sc_int.nextInt();
        if (zip > 99999) zip = 99999;
        System.out.print("Stadt:             ");
        String city = sc_String.nextLine();
        System.out.print("GeburtsTAG:        ");
        int gebTag = sc_int.nextInt();
        if (gebTag > 31) gebTag = 28;
        System.out.print("GeburtsMONAT:      ");
        int gebMonat = sc_int.nextInt();
        if (gebMonat > 12) gebMonat = 12;
        System.out.print("GeburtsJAHR:       ");
        int gebJahr = sc_int.nextInt();
        if (gebJahr < 1970) gebJahr = 1970;
        if (gebJahr > 9999) gebJahr = 2020;
        System.out.print("PIN-Code:          ");
        String pinCode = sc_String.nextLine();
        System.out.print("Email:             ");
        String email = sc_String.nextLine();
        System.out.print("KreditKartenNr:    ");
        long creditCardNr = sc_int.nextLong();
        if (creditCardNr > 9999999999999999L) creditCardNr = 9999999999999999L;
        System.out.print("CVC:               ");
        int cvc = sc_int.nextInt();
        if (cvc > 999) cvc = 999;
        System.out.print("gültig bis Jahr:   ");
        int expiryDateYear = sc_int.nextInt();
        if (expiryDateYear > 9999) expiryDateYear = 2030;
        System.out.print("       bis Monat:  ");
        int expiryDateMonth = sc_int.nextInt();
        if (expiryDateMonth > 12) expiryDateMonth = 12;
        Customer temp = new Customer(0, pinCode, email, firstName, lastName, Timestamp.valueOf(LocalDateTime.of(gebJahr, gebMonat, gebTag, 0, 0)), street, apNr, zip, city, creditCardNr, cvc, expiryDateYear, expiryDateMonth);
        return temp;
    }

    private int inputIdCustomer(CustomerDAO cuDAO, DiverseLists diLi, String meldung) {
        diLi.createListeCustomerAllRecords(cuDAO);
        Scanner sc_int = new Scanner(System.in);
        int idCustomer;
        boolean eingabegueltig;
        do {
            eingabegueltig = true;
            System.out.print("Bitte gib die IdCustomer vom zu " + meldung + "Kunden/Customer aus der vorstehenden Liste ein! ");
            idCustomer = sc_int.nextInt();
            if (idCustomer == 0) {
                System.out.println("Zurück zur Auswahl ohne Auswahl des zu " + meldung + " Kunden.");
            } else {
                boolean customerIsInTable = cuDAO.checkIsXxxIdInTableXxx("idCustomer", "customer", idCustomer);
                if (!customerIsInTable) {
                    System.out.println("Die Eingabe war ungültig, bitte wiederholen! ");
                    eingabegueltig = false;
                }
            }
        } while (!eingabegueltig);
        return idCustomer;
    }

    public void createNewRecordCustomer(CustomerDAO cuDAO) {
        System.out.println("Erfassung der notwendigen Daten für einen neuen Eintrag in customer");
        Customer temp = inputDataCustomer();
        cuDAO.createRecordCustomer(temp.getPinCode(), temp.getEmail(), temp.getFirstName(), temp.getLastName(), temp.getBirthDay().toLocalDateTime(),
                temp.getStreet(), temp.getApNr(), temp.getZip(), temp.getCity(), temp.getCreditCardNr(), temp.getCvc(), temp.getExpiryDateYear(),
                temp.getExpiryDateMonth());
    }

    public void editRecordCustomer(CustomerDAO cuDAO, DiverseLists diLi) {
        int zuEditierendeIdCustomer = inputIdCustomer(cuDAO, diLi, "editierende");
        if (zuEditierendeIdCustomer > 0) {
            Customer temp = inputDataCustomer();
            cuDAO.updateRecordCustomer(temp.getPinCode(), temp.getEmail(), temp.getFirstName(), temp.getLastName(), temp.getBirthDay().toLocalDateTime(),
                    temp.getStreet(), temp.getApNr(), temp.getZip(), temp.getCity(), temp.getCreditCardNr(), temp.getCvc(), temp.getExpiryDateYear(),
                    temp.getExpiryDateMonth(), zuEditierendeIdCustomer);
        }
    }

    public void deleteRecordCustomer(CustomerDAO cuDAO, DiverseLists diLi) {
        int zuLoeschendeIdCustomer = inputIdCustomer(cuDAO, diLi, "löschende");
        if (zuLoeschendeIdCustomer > 0) {
            boolean isCustomerInLoaned = cuDAO.checkIsXxxIdInTableXxx("idCustomer", "loaned", zuLoeschendeIdCustomer);
            if (isCustomerInLoaned) {
                System.out.println("Der Kunde hat Bücher ausgeliehen (er ist in loaned geführt), ein Löschen des Kunden ist nicht möglich.");
            } else {
                cuDAO.createQueryDeleteID("customer", "idCustomer", zuLoeschendeIdCustomer);
            }
        }
    }

    private Loaned inputDataLoanedWithOutReturn(CustomerDAO cuDAO, BookDAO boDAO, DiverseLists diLi) {
        System.out.println("Stelle sicher, dass die Buch-/Book- und die Kunden-/Customer-ID für den zu erfassenden Leihvorgang bekannt ist, bei entsprechenden Zeitressourcen wird hier weitergearbeitet.");
        Scanner sc_int = new Scanner(System.in);
        System.out.print("ID Kunde/customer:   ");
        int idCustomer = inputIdCustomer(cuDAO, diLi, "aufzunehmenden");
        if (idCustomer == 0) idCustomer = 1;
        System.out.print("ID Buch/book:        ");
        int idBook = inputIdBook(boDAO, diLi, "aufzunehmenden");
        if (idBook == 0) idBook = 1;
        System.out.print("Ausleih-TAG:         ");
        int leihTag = sc_int.nextInt();
        if (leihTag > 31) leihTag = 28;
        System.out.print("Ausleih-MONAT:       ");
        int leihMonat = sc_int.nextInt();
        if (leihMonat > 12) leihMonat = 12;
        System.out.print("Ausleih-JAHR:        ");
        int leihJahr = sc_int.nextInt();
        if (leihJahr < 1970) leihJahr = 1970;
        if (leihJahr > 3000) leihJahr = 2020;
        Loaned temp = new Loaned(0, idCustomer, idBook, LocalDateTime.of(leihJahr, leihMonat, leihTag, 0, 0), null, false, null, null);
        return temp;
    }

    private Loaned inputDataLoanedWithReturn(CustomerDAO cuDAO, BookDAO boDAO, DiverseLists diLi) {
        System.out.println("Stelle sicher, dass die Buch-/Book- und die Kunden-/Customer-ID für den zu erfassenden Leihvorgang bekannt ist, bei entsprechenden Zeitressourcen wird hier weitergearbeitet.");
        Scanner sc_String = new Scanner(System.in);
        Scanner sc_int = new Scanner(System.in);
        System.out.print("ID Kunde/customer:   ");
        int idCustomer = inputIdCustomer(cuDAO, diLi, "aufzunehmenden");
        if (idCustomer == 0) idCustomer = 1;
        System.out.print("ID Buch/book:        ");
        int idBook = inputIdBook(boDAO, diLi, "aufzunehmenden");
        if (idBook == 0) idBook = 1;
        System.out.print("Ausleih-TAG:         ");
        int leihTag = sc_int.nextInt();
        if (leihTag > 31) leihTag = 28;
        System.out.print("Ausleih-MONAT:       ");
        int leihMonat = sc_int.nextInt();
        if (leihMonat > 12) leihMonat = 12;
        System.out.print("Ausleih-JAHR:        ");
        int leihJahr = sc_int.nextInt();
        if (leihJahr < 1970) leihJahr = 1970;
        if (leihJahr > 3000) leihJahr = 2020;
        System.out.print("Rückgabe-TAG:        ");
        int rueckgabeTag = sc_int.nextInt();
        if (rueckgabeTag > 31) rueckgabeTag = 28;
        System.out.print("Rückgabe-MONAT:      ");
        int rueckgabeMonat = sc_int.nextInt();
        if (rueckgabeMonat > 12) rueckgabeMonat = 12;
        System.out.print("Rückgabe-JAHR:       ");
        int rueckgabeJahr = sc_int.nextInt();
        if (rueckgabeJahr < 1970) rueckgabeJahr = 1970;
        if (rueckgabeJahr > 3000) rueckgabeJahr = 2020;
        System.out.print("Verlängerung? j/n    ");
        String verlaengerung = sc_String.nextLine();
        boolean verlaengerung2;
        verlaengerung2 = verlaengerung.toUpperCase().contains("Y") || verlaengerung.toUpperCase().contains("J");
        Loaned temp = new Loaned(0, idCustomer, idBook, LocalDateTime.of(leihJahr, leihMonat, leihTag, 0, 0), LocalDateTime.of(rueckgabeJahr, rueckgabeMonat, rueckgabeTag, 0, 0), verlaengerung2, null, null);
        return temp;
    }

    private int inputIdLoaned(LoanedDAO loDAO, DiverseLists diLi, String meldung) {
        diLi.createListeLoanedAllRecords(loDAO);
        Scanner sc_int = new Scanner(System.in);
        int idLoaned;
        boolean eingabegueltig;
        do {
            eingabegueltig = true;
            System.out.print("Bitte gib die IdLoaned vom zu " + meldung + " Leihvorgang aus der vorstehenden Liste ein! ");
            idLoaned = sc_int.nextInt();
            if (idLoaned == 0) {
                System.out.println("Zurück zur Auswahl ohne Auswahl des zu " + meldung + " Ausleihvorgangs.");
            } else {
                boolean loanedIsInTable = loDAO.checkIsXxxIdInTableXxx("idLoaned", "loaned", idLoaned);
                if (!loanedIsInTable) {
                    System.out.println("Die Eingabe war ungültig, bitte wiederholen! ");
                    eingabegueltig = false;
                }
            }
        } while (!eingabegueltig);
        return idLoaned;
    }

    public void createNewRecordLoaned(LoanedDAO loDAO, CustomerDAO cuDAO, BookDAO boDAO, DiverseLists diLi) {
        System.out.println("Erfassung der notwendigen Daten für einen neuen Eintrag in loaned");
        Loaned temp = inputDataLoanedWithOutReturn(cuDAO, boDAO, diLi);
        loDAO.createRecordLoanedWithoutReturn(temp.getIdCustomer(), temp.getIdBook(), temp.getLoanedOn());
    }

    public void editRecordLoaned(LoanedDAO loDAO, CustomerDAO cuDAO, BookDAO boDAO, DiverseLists diLi) {
        int zueditierendeIdLoaned = inputIdLoaned(loDAO, diLi, "editierende");
        if (zueditierendeIdLoaned > 0) {
            Loaned temp = inputDataLoanedWithReturn(cuDAO, boDAO, diLi);
            loDAO.updateRecordLoaned(temp.getIdCustomer(), temp.getIdBook(), temp.getLoanedOn(), temp.getReturnedOn(), temp.isExtraTime(), zueditierendeIdLoaned);
        }
    }

    public void deleteRecordLoaned(LoanedDAO loDAO, DiverseLists diLi) {
        int zuLoeschendeIdLoaned = inputIdLoaned(loDAO, diLi, "löschende");
        if (zuLoeschendeIdLoaned > 0) {
            loDAO.createQueryDeleteID("loaned", "idLoaned", zuLoeschendeIdLoaned);
        }
    }
}