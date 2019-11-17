package controller;

import daos.*;
import entities.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Scanner;

public class RecordNewUpdateDelete {

    public Book inputDataBook() {
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
        Book temp = new Book(0, title, idAuthor, idCategory, isbn, fsk, publisher, edition, firstEdition, amountPages, language, idRow, idColumn, null, null);
        return temp;
    }

    public int inputIdBook(BookDAO boDAO, DiverseLists diLi, String meldung) {
        diLi.createListeBookAllRecords(boDAO);
        Scanner sc_int = new Scanner(System.in);
        int zuLoeschendeIdBook = 0;
        boolean eingabegueltig = true;
        do {
            eingabegueltig = true;
            System.out.print("Bitte gib die IdBook vom zu " + meldung + " Buch aus der vorstehenden Liste ein! ");
            zuLoeschendeIdBook = sc_int.nextInt();
            boolean bookIsInTable = boDAO.checkIsIDBookInTable(zuLoeschendeIdBook);
            if (!bookIsInTable) {
                System.out.println("Die Eingabe war ungültig, bitte wiederholen! ");
                eingabegueltig = false;
            }
        } while (!eingabegueltig);
        return zuLoeschendeIdBook;
    }

    public void createNewRecordBook(BookDAO boDAO) {
        System.out.println("Erfassung der notwendigen Daten für einen neuen Eintrag in book");
        Book temp = inputDataBook();
        boDAO.createRecordBook(temp.getTitle(), temp.getIdAuthor(), temp.getIdCategory(), temp.getIsbn(), temp.getFsk(),
                temp.getPublisher(), temp.getEdition(), temp.getFirstEdition(), temp.getAmountPages(), temp.getLanguage(),
                temp.getIdRow(), temp.getIdColumn());
    }

    public void editRecordBook(BookDAO boDAO, DiverseLists diLi) {
        int zuEditierendeIdBook = inputIdBook(boDAO, diLi, "editierenden");
        Book temp = inputDataBook();
        boDAO.updateRecordBook(temp.getTitle(), temp.getIdAuthor(), temp.getIdCategory(), temp.getIsbn(), temp.getFsk(),
                temp.getPublisher(), temp.getEdition(), temp.getFirstEdition(), temp.getAmountPages(), temp.getLanguage(),
                temp.getIdRow(), temp.getIdColumn(), zuEditierendeIdBook);
    }

    public void deleteRecordBook(BookDAO boDAO, DiverseLists diLi) {
        int zuLoeschendeIdBook = inputIdBook(boDAO, diLi, "löschenden");
        boDAO.createQueryDeleteID("book", "IdBook", zuLoeschendeIdBook);
    }

    public Author inputDataAuthor() {
        Scanner sc_String = new Scanner(System.in);
        Scanner sc_int = new Scanner(System.in);
        System.out.print("Vorname:           ");
        String firstName = sc_String.nextLine();
        System.out.print("Nachname:          ");
        String lastName = sc_String.nextLine();
        System.out.print("GeburtsJAHR:       ");
        int gebJahr = sc_int.nextInt();
        Author temp = new Author(0, firstName, lastName, gebJahr, null, null);
        return temp;
    }

    public int inputIdAuthor(AuthorDAO auDAO, DiverseLists diLi, String meldung) {
        diLi.createListeAuthorAllRecords(auDAO);
        Scanner sc_int = new Scanner(System.in);
        int zuLoeschendeIdAuthor = 0;
        boolean eingabegueltig = true;
        do {
            eingabegueltig = true;
            System.out.print("Bitte gib die IdAuthor vom zu " + meldung + " Author aus der vorstehenden Liste ein! ");
            zuLoeschendeIdAuthor = sc_int.nextInt();
            boolean authorIsInTable = auDAO.checkIsIDAuthorInTable(zuLoeschendeIdAuthor);
            if (!authorIsInTable) {
                System.out.println("Die Eingabe war ungültig, bitte wiederholen! ");
                eingabegueltig = false;
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
        Author temp = inputDataAuthor();
        auDAO.updateRecordAuthor(temp.getFirstName(), temp.getLastName(), temp.getBirthYear(), zuEditierendeIdAuthor);
    }

    public void deleteRecordAuthor(AuthorDAO auDAO, DiverseLists diLi) {
        int zuLoeschendeIdAuthor = inputIdAuthor(auDAO, diLi, "löschenden");
        boolean isAuthorInBook = auDAO.checkIsIdInTableBook("idAuthor", zuLoeschendeIdAuthor);
        if (isAuthorInBook) {
            System.out.println("Von diesem Autor sind noch Bücher in der Liste book, deswegen ist ein Löschen des Autors nicht möglich.");
        } else {
            auDAO.createQueryDeleteID("author", "IdAuthor", zuLoeschendeIdAuthor);
        }
    }

    public Category inputDataCategory() {
        Scanner sc_String = new Scanner(System.in);
        System.out.print("Beschreibung der Kategorie: ");
        String description = sc_String.nextLine();
        Category temp = new Category(0, description, null, null);
        return temp;
    }

    public int inputIdCategory(CategoryDAO caDAO, DiverseLists diLi, String meldung) {
        diLi.createListeCategoryAllRecords(caDAO);
        Scanner sc_int = new Scanner(System.in);
        int idCategory = 0;
        boolean eingabegueltig = true;
        do {
            eingabegueltig = true;
            System.out.print("Bitte gib die IdKategorie von dem zu " + meldung + " Eintrag aus der vorstehenden Liste ein! ");
            idCategory = sc_int.nextInt();
            boolean categoryIsInTable = caDAO.checkIsIDCategoryInTable(idCategory);
            if (!categoryIsInTable) {
                System.out.println("Die Eingabe war ungültig, bitte wiederholen! ");
                eingabegueltig = false;
            }
        } while (!eingabegueltig);
        return idCategory;
    }

    public void createNewRecordCategory(CategoryDAO caDAO) {
        System.out.println("Erfassung der notwendigen Daten für einen neuen Eintrag in category");
        Category temp = inputDataCategory();
        caDAO.createRecordCategory(temp.getDescription());
    }

    public void editRecordCategory(CategoryDAO caDAO, DiverseLists diLi) {
        int zuEditierendeIdCategory = inputIdCategory(caDAO, diLi, "editierenden");
        Category temp = inputDataCategory();
        caDAO.updateRecordCategory(temp.getDescription(), zuEditierendeIdCategory);
    }

    public void deleteRecordCategory(CategoryDAO caDAO, DiverseLists diLi) {
        int zuLoeschendeIdCategory = inputIdCategory(caDAO, diLi, "löschenden");
        boolean isCategoryInBook = caDAO.checkIsIdInTableBook("idCategory", zuLoeschendeIdCategory);
        if (isCategoryInBook) {
            System.out.println("In dieser Kategorie sind noch Bücher in der Liste book, deswegen ist ein Löschen dieser Kategorie nicht möglich.");
        } else {
            caDAO.createQueryDeleteID("category", "idCategory", zuLoeschendeIdCategory);
        }
    }

    public Customer inputDataCustomer() {
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
        if (zip > 99999) {
            zip = 99999;
        }
        System.out.print("Stadt:             ");
        String city = sc_String.nextLine();
        System.out.print("GeburtsTAG:        ");
        int gebTag = sc_int.nextInt();
        if (gebTag > 31) {
            gebTag = 28;
        }
        System.out.print("GeburtsMONAT:      ");
        int gebMonat = sc_int.nextInt();
        if (gebMonat > 12) {
            gebMonat = 12;
        }
        System.out.print("GeburtsJAHR:       ");
        int gebJahr = sc_int.nextInt();
        if (gebJahr < 1970) {
            gebJahr = 1970;
        }
        System.out.print("PIN-Code:          ");
        String pinCode = sc_String.nextLine();
        System.out.print("Email:             ");
        String email = sc_String.nextLine();
        System.out.print("KreditKartenNr:    ");
        long creditCardNr = sc_int.nextLong();
        if (creditCardNr > 9999999999999999L) {
            creditCardNr = 9999999999999999L;
        }
        System.out.print("CVC:               ");
        int cvc = sc_int.nextInt();
        if (cvc > 999) {
            cvc = 999;
        }
        System.out.print("gültig bis Jahr:   ");
        int expiryDateYear = sc_int.nextInt();
        System.out.print("       bis Monat:  ");
        int expiryDateMonth = sc_int.nextInt();
        if (expiryDateMonth > 12) {
            expiryDateMonth = 12;
        }
        Customer temp = new Customer(0, pinCode, email, firstName, lastName, Timestamp.valueOf(LocalDateTime.of(gebJahr, gebMonat, gebTag, 0, 0)), street, apNr, zip, city, creditCardNr, cvc, expiryDateYear, expiryDateMonth);
        return temp;
    }

    public int inputIdCustomer(CustomerDAO cuDAO, DiverseLists diLi, String meldung) {
        diLi.createListeCustomerAllRecords(cuDAO);
        Scanner sc_int = new Scanner(System.in);
        int idCustomer = 0;
        boolean eingabegueltig = true;
        do {
            eingabegueltig = true;
            System.out.print("Bitte gib die IdCustomer vom zu löschenden Kunden/Customer aus der vorstehenden Liste ein! ");
            idCustomer = sc_int.nextInt();
            boolean customerIsInTable = cuDAO.checkIsIDCustomerInTable(idCustomer);
            if (!customerIsInTable) {
                System.out.println("Die Eingabe war ungültig, bitte wiederholen! ");
                eingabegueltig = false;
            }
        } while (!eingabegueltig);
        return idCustomer;
    }

    public void createNewRecordCustomer(CustomerDAO cuDAO) {
        System.out.println("Erfassung der notwendigen Daten für einen neuen Eintrag in customer"); //TODO mögliche Fehleingaben bei der Erfassung abfangen, wenn mal Zeit ist
        Customer temp = inputDataCustomer();
        cuDAO.createRecordCustomer(temp.getPinCode(), temp.getEmail(), temp.getFirstName(), temp.getLastName(), temp.getBirthDay().toLocalDateTime(),
                temp.getStreet(), temp.getApNr(), temp.getZip(), temp.getCity(), temp.getCreditCardNr(), temp.getCvc(), temp.getExpiryDateYear(),
                temp.getExpiryDateMonth());
    }

    public void editRecordCustomer(CustomerDAO cuDAO, DiverseLists diLi) {
        int zuEditierendeIdCustomer = inputIdCustomer(cuDAO, diLi, "editierende");
        Customer temp = inputDataCustomer();
        cuDAO.updateRecordCustomer(temp.getPinCode(), temp.getEmail(), temp.getFirstName(), temp.getLastName(), temp.getBirthDay().toLocalDateTime(),
                temp.getStreet(), temp.getApNr(), temp.getZip(), temp.getCity(), temp.getCreditCardNr(), temp.getCvc(), temp.getExpiryDateYear(),
                temp.getExpiryDateMonth(), zuEditierendeIdCustomer);
    }

    public void deleteRecordCustomer(CustomerDAO cuDAO, DiverseLists diLi) {
        int zuLoeschendeIdCustomer = inputIdCustomer(cuDAO, diLi, "löschende");
        boolean isCustomerInBook = cuDAO.checkIsIdInTableBook("idCustomer", zuLoeschendeIdCustomer);
        if (isCustomerInBook) {
            System.out.println("In dieser Kategorie sind noch Bücher in der Liste book, deswegen ist ein Löschen dieser Kategorie nicht möglich.");
        } else {
            cuDAO.createQueryDeleteID("customer", "idCustomer", zuLoeschendeIdCustomer);
        }
    }

    public Loaned inputDataLoanedWithOutReturn() {
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
        Loaned temp = new Loaned(0, idCustomer, idBook, LocalDateTime.of(leihJahr, leihMonat, leihTag, 0, 0), null, false, null, null);
        return temp;
    }

    public Loaned inputDataLoanedWithReturn() {
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
        System.out.print("Rückgabe-TAG:        ");
        int rueckgabeTag = sc_int.nextInt();
        System.out.print("Rückgabe-MONAT:      ");
        int rueckgabeMonat = sc_int.nextInt();
        System.out.print("Rückgabe-JAHR:       ");
        int rueckgabeJahr = sc_int.nextInt();
        System.out.print("Verlängerung? j/n    ");
        String verlaengerung = sc_String.nextLine();
        boolean verlaengerung2;
        if (verlaengerung.toUpperCase().contains("Y") || verlaengerung.toUpperCase().contains("J")) {
            verlaengerung2 = true;
        } else {
            verlaengerung2 = false;
        }
        Loaned temp = new Loaned(0, idCustomer, idBook, LocalDateTime.of(leihJahr, leihMonat, leihTag, 0, 0), LocalDateTime.of(rueckgabeJahr, rueckgabeMonat, rueckgabeTag, 0, 0), verlaengerung2, null, null);
        return temp;
    }

    public int inputIdLoaned(LoanedDAO loDAO, DiverseLists diLi, String meldung) {
        diLi.createListeLoanedAllRecords(loDAO);
        Scanner sc_int = new Scanner(System.in);
        int idLoaned = 0;
        boolean eingabegueltig = true;
        do {
            eingabegueltig = true;
            System.out.print("Bitte gib die IdLoaned vom zu " + meldung + " Leihvorgang aus der vorstehenden Liste ein! ");
            idLoaned = sc_int.nextInt();
            boolean loanedIsInTable = loDAO.checkIsIDLoanedInTable(idLoaned);
            if (!loanedIsInTable) {
                System.out.println("Die Eingabe war ungültig, bitte wiederholen! ");
                eingabegueltig = false;
            }
        } while (!eingabegueltig);
        return idLoaned;
    }

    public void createNewRecordLoaned(LoanedDAO loDAO) {
        System.out.println("Erfassung der notwendigen Daten für einen neuen Eintrag in loaned"); //TODO mögliche Fehleingaben bei der Erfassung abfangen, wenn mal Zeit ist
        Loaned temp = inputDataLoanedWithOutReturn();
        loDAO.createRecordLoanedWithoutReturn(temp.getIdCustomer(), temp.getIdBook(), temp.getLoanedOn());
    }

    public void editRecordLoaned(LoanedDAO loDAO, DiverseLists diLi) {//fehlt noch
        int zueditierendeIdLoaned = inputIdLoaned(loDAO, diLi, "editierende");
        Loaned temp = inputDataLoanedWithReturn();
        loDAO.updateRecordLoaned(temp.getIdCustomer(), temp.getIdBook(), temp.getLoanedOn(), temp.getReturnedOn(), temp.isExtraTime(), zueditierendeIdLoaned);
    }

    public void deleteRecordLoaned(LoanedDAO loDAO, DiverseLists diLi) {
        int zuLoeschendeIdLoaned = inputIdLoaned(loDAO, diLi, "löschende");
        loDAO.createQueryDeleteID("loaned", "idLoaned", zuLoeschendeIdLoaned);
    }
}







