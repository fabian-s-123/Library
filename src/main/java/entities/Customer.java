package entities;

import java.sql.Timestamp;

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
    private int creditCardNr;
    private int CVC;
    private int expiryDateYear;
    private int getExpiryDateMonth;


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

    public Customer(int idCustomer, String pinCode, String email, String firstName, String lastName, Timestamp birthDay, String street, String apNr, int zip, String city, int creditCardNr, int CVC, int expiryDateYear, int getExpiryDateMonth) {
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
        this.CVC = CVC;
        this.expiryDateYear = expiryDateYear;
        this.getExpiryDateMonth = getExpiryDateMonth;
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

    public int getCreditCardNr() {
        return creditCardNr;
    }

    public void setCreditCardNr(int creditCardNr) {
        this.creditCardNr = creditCardNr;
    }

    public int getCVC() {
        return CVC;
    }

    public void setCVC(int CVC) {
        this.CVC = CVC;
    }

    public int getExpiryDateYear() {
        return expiryDateYear;
    }

    public void setExpiryDateYear(int expiryDateYear) {
        this.expiryDateYear = expiryDateYear;
    }

    public int getGetExpiryDateMonth() {
        return getExpiryDateMonth;
    }

    public void setGetExpiryDateMonth(int getExpiryDateMonth) {
        this.getExpiryDateMonth = getExpiryDateMonth;
    }
}
