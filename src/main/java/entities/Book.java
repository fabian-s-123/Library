package entities;

import java.time.LocalDateTime;

public class Book {
    private int idBook;
    private String title;
    private int idAuthor;
    private int idCategory;
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


    public Book(int idBook, String title, int idAuthor, int idCategory, long isbn, int fsk, String publisher, String edition, String firstEdition, int amountPages, String language, int idRow, int idColumn) {
        this.idBook = idBook;
        this.title = title;
        this.idAuthor = idAuthor;
        this.idCategory = idCategory;
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

}









