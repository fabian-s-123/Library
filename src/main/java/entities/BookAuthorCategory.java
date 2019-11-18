package entities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class BookAuthorCategory {
    private int idBook;
    private String title;
    private int idAuthor;
    private String firstName;
    private String lastName;
    private int birthYear;
    private int idCategory;
    private String description;
    private long isbn;
    private int fsk;
    private String publisher;
    private String edition;
    private String firstEdition;
    private int amountPages;
    private String language;
    private int idRow;
    private int idColumn;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;

    public BookAuthorCategory(int idBook, String title, int idAuthor, String firstName, String lastName, int birthYear, int idCategory, String description, long isbn, int fsk, String publisher, String edition, String firstEdition, int amountPages, String language, int idRow, int idColumn, LocalDateTime created_at, LocalDateTime updated_at) {
        this.idBook = idBook;
        this.title = title;
        this.idAuthor = idAuthor;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthYear = birthYear;
        this.idCategory = idCategory;
        this.description = description;
        this.isbn = isbn;
        this.fsk = fsk;
        this.publisher = publisher;
        this.edition = edition;
        this.firstEdition = firstEdition;
        this.amountPages = amountPages;
        this.language = language;
        this.idRow = idRow;
        this.idColumn = idColumn;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public BookAuthorCategory(int idBook, String title, int idAuthor, String firstName, String lastName, int birthYear, int idCategory, String description, long isbn, int fsk, String publisher, String edition, String firstEdition, int amountPages, String language, int idRow, int idColumn) {
        this.idBook = idBook;
        this.title = title;
        this.idAuthor = idAuthor;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthYear = birthYear;
        this.idCategory = idCategory;
        this.description = description;
        this.isbn = isbn;
        this.fsk = fsk;
        this.publisher = publisher;
        this.edition = edition;
        this.firstEdition = firstEdition;
        this.amountPages = amountPages;
        this.language = language;
        this.idRow = idRow;
        this.idColumn = idColumn;
    }

    public BookAuthorCategory() {
    }

    public void ausgabeKopfListBookAuthorCategory() {
        System.out.println("Lfd ID    Titel                                              Vorname         Name            Geb.  Kategorie                     ISBN          FSK  Verlag                    Edition               Sei-    Sprache    erstellt             updated");
        System.out.println("Nr  book                                                     Author                          Jahr  ID Beschreibung                                                                      First       ten                am                   am");
        System.out.println("______________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________\n");
    }

    public void ausgabeZeileListBookAuthorCategory(BookAuthorCategory temp, int i) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yy  HH:mm:ss");
        System.out.printf("%-3d %-5d %-50s %-15s %-15s %4d %2d   %-25s %-13d  %2d  %-25s %-10s %-10s %-5d   %-10s %18s   %18s\n", +
                i, temp.idBook, temp.title, temp.firstName, temp.lastName, temp.birthYear, temp.idCategory, temp.description, temp.isbn, temp.fsk, temp.publisher, temp.edition, temp.firstEdition, temp.amountPages, temp.language, temp.created_at.format(dtf), temp.updated_at.format(dtf));
    }

    public void ausgabeListBookAuthorCategory(List<BookAuthorCategory> listAllBooks) {
        ausgabeKopfListBookAuthorCategory();
        for (int i = 0; i < listAllBooks.size(); i++) {
            BookAuthorCategory temp = listAllBooks.get(i);
            ausgabeZeileListBookAuthorCategory(temp, i + 1);
        }
        System.out.print("Ende der Liste\n\n");
    }

    /**
     * Version Fabian
     */
    public void printHeadBAC() {
        System.out.println("ID    Title                                              First Name      Last Name       Y.o.  Category                      ISBN           FSK Publisher                 Current    First      Pages   Language");
        System.out.println("book                                                     Author          Author          Birth ID  Description                                                            Edition    Edition                    ");
        System.out.println("________________________________________________________________________________________________________________________________________________________________________________________________________________");
    }

    public void printListBAC(BookAuthorCategory temp) {
        System.out.printf("%-5d %-50s %-15s %-15s %4d %2d   %-25s %-13d  %2d  %-25s %-10s %-10s %-5d   %-10s\n", +
                temp.idBook, temp.title, temp.firstName, temp.lastName, temp.birthYear, temp.idCategory, temp.description, temp.isbn, temp.fsk, temp.publisher, temp.edition, temp.firstEdition, temp.amountPages, temp.language);
    }

    public void printListBAC(List<BookAuthorCategory> listAllBooks) {
        for (int i = 0; i < listAllBooks.size(); i++) {
            BookAuthorCategory temp = listAllBooks.get(i);
            printListBAC(temp);
        }
    }

    /**
     * End Version Fabian
     */

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

    public int getIdAuthor() {
        return idAuthor;
    }

    public void setIdAuthor(int idAuthor) {
        this.idAuthor = idAuthor;
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

    public int getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getIsbn() {
        return isbn;
    }

    public void setIsbn(long isbn) {
        this.isbn = isbn;
    }

    public int getFsk() {
        return fsk;
    }

    public void setFsk(int fsk) {
        this.fsk = fsk;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public String getFirstEdition() {
        return firstEdition;
    }

    public void setFirstEdition(String firstEdition) {
        this.firstEdition = firstEdition;
    }

    public int getAmountPages() {
        return amountPages;
    }

    public void setAmountPages(int amountPages) {
        this.amountPages = amountPages;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getIdRow() {
        return idRow;
    }

    public void setIdRow(int idRow) {
        this.idRow = idRow;
    }

    public int getIdColumn() {
        return idColumn;
    }

    public void setIdColumn(int idColumn) {
        this.idColumn = idColumn;
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

    @Override
    public String toString() {
        return "'" + idBook + "', '" + title + "', '" + idAuthor + "', '" + firstName + "', '" + lastName + "', '" + birthYear + "', '" + idCategory + "', '" + description + "', '" + isbn + "', '" + fsk + "', '" + publisher + "', '" + edition + ", '" + firstEdition
                + "', '" + amountPages + "', '" + language + "', '" + idRow + "', '" + idColumn + "'";
    }
}

