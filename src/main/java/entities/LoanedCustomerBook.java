package entities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;

public class LoanedCustomerBook {

    private int idLoaned;
    private int idCustomer;
    private String customerFirstName;
    private String customerLastName;
    private int idBook;
    private String title;
    private String authorFirstName;
    private String authorLastName;
    private LocalDateTime loanedOn;
    private LocalDateTime returnedOn;
    private boolean extraTime;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;

    public LoanedCustomerBook(int idLoaned, int idCustomer, String customerFirstName, String customerLastName, int idBook, String title, String authorFirstName, String authorLastName, LocalDateTime loanedOn, LocalDateTime returnedOn, boolean extraTime, LocalDateTime created_at, LocalDateTime updated_at) {
        this.idLoaned = idLoaned;
        this.idCustomer = idCustomer;
        this.customerFirstName = customerFirstName;
        this.customerLastName = customerLastName;
        this.idBook = idBook;
        this.title = title;
        this.authorFirstName = authorFirstName;
        this.authorLastName = authorLastName;
        this.loanedOn = loanedOn;
        this.returnedOn = returnedOn;
        this.extraTime = extraTime;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public void ausgabeKopfListLCB() {
        System.out.println("ID    ID      Vorname         Name           ID    Titel                                              Vorname         Name                entliehen              zurückgegeben        ver-  erstellt               updated");
        System.out.println("loan  cust.   customer                      book                                                      Author                              am                     am                   län-  am                     am");
    }

    public void ausgabeZeileListLCB(LoanedCustomerBook temp) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yy  HH:mm:ss");
        System.out.printf("%-5d  %-4d   %-15s %-15s %-4d %-50s %-15s %-15s     %18s     ", temp.idLoaned, temp.idCustomer, temp.customerFirstName, temp.customerLastName, temp.idBook, temp.title, temp.authorFirstName, temp.authorLastName, temp.loanedOn.format(dtf));
        if (temp.returnedOn != null) {
            System.out.print(temp.returnedOn.format(dtf));
        } else {
            System.out.print("offen             ");
        }
        System.out.print((temp.extraTime) ? "   X   " : "   -   ");
        System.out.printf("  %18s     %18s\n", temp.created_at.format(dtf), temp.updated_at.format(dtf));
    }


    public void ausgabeListLCB1(LinkedList<LoanedCustomerBook> listLCB) { //1 = nur offene Leihvorgänge (offene Leihvorgänge sortiert nach Kunden)
        ausgabeKopfListLCB();
        for (int i = 0; i < listLCB.size(); i++) {
            LoanedCustomerBook temp = listLCB.get(i);
            if (temp.returnedOn == null) {
                ausgabeZeileListLCB(temp);
            }
        }
        System.out.print("Ende der Liste\n\n");
    }

    public void ausgabeListLCB2(LinkedList<LoanedCustomerBook> listLCB) {  //2 = alle Leihvorgänge (Leihvorgänge sortiert nach Kunden)
        ausgabeKopfListLCB();
        for (int i = 0; i < listLCB.size(); i++) {
            LoanedCustomerBook temp = listLCB.get(i);
            ausgabeZeileListLCB(temp);
        }
        System.out.print("Ende der Liste\n\n");
    }

    public void ausgabeListLCB3(LinkedList<LoanedCustomerBook> listLCB) {  //2 = welche Bücher sind derzeit ausgeliehen (offene Leihvorgänge sortiert nach Bücher)
        ausgabeKopfListLCB();
        for (int i = 0; i < listLCB.size(); i++) {
            LoanedCustomerBook temp = listLCB.get(i);
            if (temp.returnedOn == null) {
                ausgabeZeileListLCB(temp);
            }
        }
        System.out.print("Ende der Liste\n\n");
    }

    public void ausgabeListLCB5(LinkedList<LoanedCustomerBook> listLCB) {  //2 = welche Bücher wurden überhaupt verliehen (Leihvorgänge sortiert nach Bücher)
        ausgabeKopfListLCB();
        for (int i = 0; i < listLCB.size(); i++) {
            LoanedCustomerBook temp = listLCB.get(i);
                ausgabeZeileListLCB(temp);
        }
        System.out.print("Ende der Liste\n\n");
    }


    public int getIdLoaned() {
        return idLoaned;
    }

    public void setIdLoaned(int idLoaned) {
        this.idLoaned = idLoaned;
    }

    public int getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(int idCustomer) {
        this.idCustomer = idCustomer;
    }

    public String getCustomerFirstName() {
        return customerFirstName;
    }

    public void setCustomerFirstName(String customerFirstName) {
        this.customerFirstName = customerFirstName;
    }

    public String getCustomerLastName() {
        return customerLastName;
    }

    public void setCustomerLastName(String customerLastName) {
        this.customerLastName = customerLastName;
    }

    public int getIdBook() {
        return idBook;
    }

    public void setIdBook(int idBook) {
        this.idBook = idBook;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthorFirstName() {
        return authorFirstName;
    }

    public void setAuthorFirstName(String authorFirstName) {
        this.authorFirstName = authorFirstName;
    }

    public String getAuthorLastName() {
        return authorLastName;
    }

    public void setAuthorLastName(String authorLastName) {
        this.authorLastName = authorLastName;
    }

    public LocalDateTime getLoanedOn() {
        return loanedOn;
    }

    public void setLoanedOn(LocalDateTime loanedOn) {
        this.loanedOn = loanedOn;
    }

    public LocalDateTime getReturnedOn() {
        return returnedOn;
    }

    public void setReturnedOn(LocalDateTime returnedOn) {
        this.returnedOn = returnedOn;
    }

    public boolean isExtraTime() {
        return extraTime;
    }

    public void setExtraTime(boolean extraTime) {
        this.extraTime = extraTime;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public LocalDateTime getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(LocalDateTime updated_at) {
        this.updated_at = updated_at;
    }
}
