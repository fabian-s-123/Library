package controller;

import daos.BookDAO;
import daos.LoanedDAO;
import entities.Book;
import entities.BookAuthorCategory;
import entities.Loaned;
import entities.LoanedCustomerBook;

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

    public void createListWelcheBuechersindderzeitimHaus(BookDAO boDAO, LoanedDAO loDAO, String meldung){
        System.out.println("Übersicht4: " + meldung);
        LinkedList<BookAuthorCategory> listAllBooks = boDAO.getListBAC();
        LinkedList<LoanedCustomerBook> listAllLCB = loDAO.getListeLCB_sortBook();
        for(int i=0; i<listAllBooks.size(); i++){
            int idBookActual = listAllBooks.get(i).getIdBook();
            System.out.println("Aktuelle Buch-ID aus ListeAllerBuecher" + idBookActual);
            for (int j=0; j<listAllLCB.size(); j++){
                System.out.println("   Aktuelle Buch-ID aus den Entleihungen" + listAllBooks.get(j).getIdBook());
                if (idBookActual == listAllBooks.get(j).getIdBook()) { //aktuelles Buch gehört zum aktuellen DS aus den Entleihungen
                    System.out.println("           die Datensätze gehören zusammen");
                    if (listAllLCB.get(j).getReturnedOn() == null) {   //aktuelles Buch ist gerade entliehen
                    System.out.println("                   Das Returned-Datum ist null");
//                        listAllBooks.remove(i);                        //aktuelles Buch aus der Bücherliste löschen, da es nicht zum Ausleihen vorhanden ist
                        j = listAllLCB.size();                         //j auf Abbruch dieser Schleife setzen
//                        i--;                                           //i um ein reduzieren, damit beim nächsten durchlauf der nächste DS genommen wird
                    }
                }
            }
        }
        listAllBooks.get(0).ausgabeListAllBooks(listAllBooks);
    }

    public void createListWelcheBuecherwurdenueberhauptverliehen(LoanedDAO loDAO, String meldung) {
        System.out.println("Übersicht5: " + meldung);
        LinkedList<LoanedCustomerBook> listLCB = loDAO.getListeLCB_sortBook();
        listLCB.get(0).ausgabeListLCB5(listLCB);
    }
}
