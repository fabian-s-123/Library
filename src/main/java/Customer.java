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



}
