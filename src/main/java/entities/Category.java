package entities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;

public class Category {
    private int idCategory;
    private String description;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;

    public Category(int idCategory, String description, LocalDateTime created_at, LocalDateTime updated_at) {
        this.idCategory = idCategory;
        this.description = description;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public void ausgabeListAllCategories(LinkedList<Category> listAllCategories) {
        ausgabeKopfListCategory();
        for (int i = 0; i < listAllCategories.size(); i++) {
            Category temp = listAllCategories.get(i);
            ausgabeZeileListCategory(temp);
        }
        System.out.print("Ende der Liste\n\n");
    }

    public void ausgabeKopfListCategory() {
        System.out.println("ID       Beschreibung                     erstellt am          updated am");
        System.out.println("_________________________________________________________________________________\n");
    }

    public void ausgabeZeileListCategory(Category temp) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yy  HH:mm:ss");
        System.out.printf("%-8d %-30s   %18s   %18s\n", +
                temp.idCategory, temp.description, temp.created_at.format(dtf), temp.updated_at.format(dtf));
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
