package controller;

import daos.*;
import entities.*;

import java.util.LinkedList;

public class DiverseLists {


    public void createListWerHatDerzeitWelcheBuecherAusgeliehen(LoanedDAO loDAO, String meldung) {
        System.out.println("Übersicht1: " + meldung);
        LinkedList<LoanedCustomerBook> listLCB = loDAO.getListeLCB_sortCustomer();
        listLCB.get(0).ausgabeListLCB1(listLCB);
    }

    public void createListWerHatUeberhauptBuecherAusgeliehen(LoanedDAO loDAO, String meldung) {
        System.out.println("Übersicht2: " + meldung);
        LinkedList<LoanedCustomerBook> listLCB = loDAO.getListeLCB_sortCustomer();
        listLCB.get(0).ausgabeListLCB2(listLCB);
    }

    public void createListWelcheBuechersindderzeitausgeliehen(LoanedDAO loDAO, String meldung) {
        System.out.println("Übersicht3: " + meldung);
        LinkedList<LoanedCustomerBook> listLCB = loDAO.getListeLCB_sortBook();
        listLCB.get(0).ausgabeListLCB3(listLCB);
    }

    public void createListWelcheBuechersindderzeitimHaus(BookDAO boDAO, LoanedDAO loDAO, String meldung) {
        System.out.println("Übersicht4: " + meldung);
        LinkedList<BookAuthorCategory> listAllBooks = boDAO.getListBAC();
        LinkedList<LoanedCustomerBook> listAllLCB = loDAO.getListeLCB_sortBook();
        for (int i = 0; i < listAllBooks.size(); i++) {
            int idBookActual = listAllBooks.get(i).getIdBook();
            //System.out.println("Aktuelle Buch-ID aus ListeAllerBuecher" + idBookActual);
            for (int j = 0; j < listAllLCB.size(); j++) {
                //System.out.println("   Aktuelle Buch-ID aus den Entleihungen" + listAllBooks.get(j).getIdBook());
                if (idBookActual == listAllLCB.get(j).getIdBook()) { //aktuelles Buch gehört zum aktuellen DS aus den Entleihungen
                    //System.out.println("           die Datensätze gehören zusammen");
                    if (listAllLCB.get(j).getReturnedOn() == null) {   //aktuelles Buch ist gerade entliehen
                        //System.out.println("                   Das Returned-Datum ist null");

                        listAllBooks.remove(i);                        //aktuelles Buch aus der Bücherliste löschen, da es nicht zum Ausleihen vorhanden ist
                        j = listAllLCB.size();                         //j auf Abbruch dieser Schleife setzen
                        i--;                                           //i um ein reduzieren, damit beim nächsten durchlauf der nächste DS genommen wird
                    }
                }
            }
        }
        listAllBooks.get(0).ausgabeListBookAuthorCategory(listAllBooks);
    }

    public void createListWelcheBuecherwurdenueberhauptverliehen(LoanedDAO loDAO, String meldung) {
        System.out.println("Übersicht5: " + meldung);
        LinkedList<LoanedCustomerBook> listLCB = loDAO.getListeLCB_sortBook();
        listLCB.get(0).ausgabeListLCB5(listLCB);
    }

    public void createListeWelcheBuecherwurdengarnichtverliehen(BookDAO boDAO, LoanedDAO loDAO, String meldung) {
        System.out.println("Übersicht6: " + meldung);
        LinkedList<BookAuthorCategory> listAllBooks = boDAO.getListBAC();
        LinkedList<LoanedCustomerBook> listAllLCB = loDAO.getListeLCB_sortBook();
        for (int i = 0; i < listAllBooks.size(); i++) {
            int idBookActual = listAllBooks.get(i).getIdBook();
            //System.out.println("Aktuelle Buch-ID aus ListeAllerBuecher" + idBookActual);
            for (int j = 0; j < listAllLCB.size(); j++) {
                //System.out.println("   Aktuelle Buch-ID aus den Entleihungen" + listAllBooks.get(j).getIdBook());
                if (idBookActual == listAllLCB.get(j).getIdBook()) { //aktuelles Buch gehört zum aktuellen DS aus den Entleihungen
                    //System.out.println("           die Datensätze gehören zusammen --> Buch wurde oder ist entliehen, Buch muss aus der Liste der noch nie verliehenen Bücher");
                    listAllBooks.remove(i);                        //aktuelles Buch aus der Bücherliste löschen, da es nicht zum Ausleihen vorhanden ist
                    j = listAllLCB.size();                         //j auf Abbruch dieser Schleife setzen
                    i--;                                           //i um ein reduzieren, damit beim nächsten durchlauf der nächste DS genommen wird
                }
            }
        }
        listAllBooks.get(0).ausgabeListBookAuthorCategory(listAllBooks);
    }

    public void createListeVomAutorsindwelcheBuechergelistet(BookDAO boDAO, String meldung) {
        System.out.println("Übersicht7: " + meldung);
        LinkedList<BookAuthorCategory> listAuthorBook = boDAO.getListAuthorBook();
        listAuthorBook.get(0).ausgabeListBookAuthorCategory(listAuthorBook);
    }

    public void createListeInwelcherKategoriesindwelcheBuechergelistet(BookDAO boDAO, String meldung) {
        System.out.println("Übersicht8: " + meldung);
        LinkedList<BookAuthorCategory> listCategoryBook = boDAO.getListCategoryBook();
        listCategoryBook.get(0).ausgabeListBookAuthorCategory(listCategoryBook);
    }

    public LinkedList<BookAuthorCategory> createListeBookAllRecords(BookDAO boDAO){
        System.out.println("Bereich book - alle Datensätze der Tabelle (mit ergänzenden Angaben aus author und category, sortiert nach BuchID");
        LinkedList<BookAuthorCategory> listAllBooks = boDAO.getListBAC();
        listAllBooks.get(0).ausgabeListBookAuthorCategory(listAllBooks);
        return listAllBooks;
    }

    public LinkedList<Author> createListeAuthorAllRecords(AuthorDAO auDAO){
        System.out.println("Bereich author - alle Datensätze der Tabelle (alphabetisch sortiert nach dem Nachnamen des Autors");
        LinkedList<Author> listAllAuthors = auDAO.getListAllAuthors();
        listAllAuthors.get(0).ausgabeListAllAuthors(listAllAuthors);
        return listAllAuthors;
    }

    public LinkedList<Category> createListeCategoryAllRecords(CategoryDAO caDAO){
        System.out.println("Bereich category - alle Datensätze der Tabelle (alphabetisch sortiert nach der Beschreibung der Kategorie");
        LinkedList<Category> listAllCategories = caDAO.getListAllCategories();
        listAllCategories.get(0).ausgabeListAllCategories(listAllCategories);
        return listAllCategories;
    }

    public LinkedList<Customer> createListeCustomerAllRecords(CustomerDAO cuDAO){
        System.out.println("Bereich customer - alle Datensätze der Tabelle (alphabetisch sortiert nach dem Nachnamen des Kunden");
        LinkedList<Customer> listAllCustomers = cuDAO.getListAllCustomers();
        listAllCustomers.get(0).ausgabeListAllCustomers(listAllCustomers);
        return listAllCustomers;
    }
    public LinkedList<LoanedCustomerBook> createListeLoanedAllRecords(LoanedDAO loDAO){
        System.out.println("Bereich loaned - alle Datensätze der Tabelle (alphabetisch sortiert nach dem Nachnamen des Kunden");
        LinkedList<LoanedCustomerBook> listLCB = loDAO.getListeLCB_sortCustomer();
        listLCB.get(0).ausgabeListLCB2(listLCB);
        return listLCB;
    }

}
