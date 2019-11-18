package entities;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;

public class Customer {

    private int idCustomer;
    private String pinCode;
    private String email;
    private String firstName;
    private String lastName;
    private Timestamp birthDay;
    private String street;
    private String apNr;
    private int zip;
    private String city;
    private long creditCardNr;
    private int cvc;
    private int expiryDateYear;
    private int expiryDateMonth;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;


    public Customer(int idCustomer, String pinCode, String email, String firstName, String lastName, Timestamp birthDay, String street, String apNr, int zip, String city) {
        this.idCustomer = idCustomer;
        this.pinCode = pinCode;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDay = birthDay;
        this.street = street;
        this.apNr = apNr;
        this.zip = zip;
        this.city = city;
    }

    public Customer(int idCustomer, String pinCode, String email, String firstName, String lastName, Timestamp birthDay, String street, String apNr, int zip, String city, long creditCardNr, int cvc, int expiryDateYear, int expiryDateMonth) {
        this.idCustomer = idCustomer;
        this.pinCode = pinCode;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDay = birthDay;
        this.street = street;
        this.apNr = apNr;
        this.zip = zip;
        this.city = city;
        this.creditCardNr = creditCardNr;
        this.cvc = cvc;
        this.expiryDateYear = expiryDateYear;
        this.expiryDateMonth = expiryDateMonth;
    }

    public Customer(int idCustomer, String pinCode, String email, String firstName, String lastName, Timestamp birthDay, String street, String apNr, int zip, String city, long creditCardNr, int cvc, int expiryDateYear, int expiryDateMonth, LocalDateTime created_at, LocalDateTime updated_at) {
        this.idCustomer = idCustomer;
        this.pinCode = pinCode;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDay = birthDay;
        this.street = street;
        this.apNr = apNr;
        this.zip = zip;
        this.city = city;
        this.creditCardNr = creditCardNr;
        this.cvc = cvc;
        this.expiryDateYear = expiryDateYear;
        this.expiryDateMonth = expiryDateMonth;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public void ausgabeListAllCustomers(LinkedList<Customer> listAllCustomers) {
        ausgabeKopfListCustomer();
        for (int i = 0; i < listAllCustomers.size(); i++) {
            Customer temp = listAllCustomers.get(i);
            ausgabeZeileListCustomer(temp);
        }
        System.out.print("Ende der Liste\n\n");
    }

    private void ausgabeKopfListCustomer() {
        System.out.println("ID   Name                      Vorname                   Straße HNr                                    PLZ   Ort                    GebDat     email                          PIN             KreditKartenNr       CVC   gültig bis  erstellt am          updated am");
        System.out.println("____________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________\n");
    }

    private void ausgabeZeileListCustomer(Customer temp) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yy  HH:mm:ss");
        DateTimeFormatter dtf_k = DateTimeFormatter.ofPattern("dd.MM.yy");
        System.out.printf("%-4d %-25s %-25s %-45s %-5d %-20s %10s   %-30s %-15s %-16d %7s   %-7s     %18s   %18s \n", +
                temp.idCustomer, temp.lastName, temp.firstName, (temp.street + " " + temp.apNr), temp.zip, temp.city, (temp.birthDay.toLocalDateTime()).format(dtf_k), temp.email, temp.pinCode, temp.creditCardNr, temp.cvc, (temp.expiryDateMonth + "/" + temp.expiryDateYear), temp.created_at.format(dtf), temp.updated_at.format(dtf));
    }

    public int getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(int idCustomer) {
        this.idCustomer = idCustomer;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Timestamp getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Timestamp birthDay) {
        this.birthDay = birthDay;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getApNr() {
        return apNr;
    }

    public void setApNr(String apNr) {
        this.apNr = apNr;
    }

    public int getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public long getCreditCardNr() {
        return creditCardNr;
    }

    public void setCreditCardNr(int creditCardNr) {
        this.creditCardNr = creditCardNr;
    }

    public int getCvc() {
        return cvc;
    }

    public void setCVC(int cvc) {
        this.cvc = cvc;
    }

    public int getExpiryDateYear() {
        return expiryDateYear;
    }

    public void setExpiryDateYear(int expiryDateYear) {
        this.expiryDateYear = expiryDateYear;
    }

    public int getExpiryDateMonth() {
        return expiryDateMonth;
    }

    public void setExpiryDateMonth(int expiryDateMonth) {
        this.expiryDateMonth = expiryDateMonth;
    }
}
