package entities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;

public class Author {
    private int idAuthor;
    private String firstName;
    private String lastName;
    private int birthYear;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;

    public Author(int idAuthor, String firstName, String lastName, int birthYear, LocalDateTime created_at, LocalDateTime updated_at) {
        this.idAuthor = idAuthor;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthYear = birthYear;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public void ausgabeListAllAuthors(LinkedList<Author> listAllAuthors){
        ausgabeKopfListAuthor();
        for (int i = 0; i < listAllAuthors.size(); i++) {
            Author temp = listAllAuthors.get(i);
            ausgabeZeileListAuthor(temp);
        }
        System.out.print("Ende der Liste\n\n");
    }

    public void ausgabeKopfListAuthor(){
        System.out.println("ID       Vorname                   Name                      Geb.    erstellt             updated");
        System.out.println("Author                                                       Jahr    am                   am");
    }

    public void ausgabeZeileListAuthor(Author temp) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yy  HH:mm:ss");
        System.out.printf("%-8d %-25s %-25s %-4d    %18s   %18s\n", +
                temp.idAuthor,  temp.firstName, temp.lastName, temp.birthYear, temp.created_at.format(dtf), temp.updated_at.format(dtf));
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
