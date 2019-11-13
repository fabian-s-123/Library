package controller;

import daos.LoanedDAO;
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

    public void createListWelcheBuecherwurdenueberhauptverliehen(LoanedDAO loDAO, String meldung) {
        System.out.println("Übersicht5: " + meldung);
        LinkedList<LoanedCustomerBook> listLCB = loDAO.getListeLCB_sortBook();
        listLCB.get(0).ausgabeListLCB5(listLCB);
    }
}
