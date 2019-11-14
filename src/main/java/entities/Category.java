package entities;

import java.time.LocalDateTime;

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
