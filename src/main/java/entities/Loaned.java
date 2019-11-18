package entities;

import java.time.LocalDateTime;

public class Loaned {
    private int idLoaned;
    private int idCustomer;
    private int idBook;
    private LocalDateTime loanedOn;
    private LocalDateTime returnedOn;
    private boolean extraTime;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;

    public Loaned(int idLoaned, int idCustomer, int idBook, LocalDateTime loanedOn, LocalDateTime returnedOn, boolean extraTime, LocalDateTime created_at, LocalDateTime updated_at) {
        this.idLoaned = idLoaned;
        this.idCustomer = idCustomer;
        this.idBook = idBook;
        this.loanedOn = loanedOn;
        this.returnedOn = returnedOn;
        this.extraTime = extraTime;
        this.created_at = created_at;
        this.updated_at = updated_at;
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

    public int getIdBook() {
        return idBook;
    }

    public void setIdBook(int idBook) {
        this.idBook = idBook;
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
