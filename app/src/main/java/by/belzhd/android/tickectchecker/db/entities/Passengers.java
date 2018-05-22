package by.belzhd.android.tickectchecker.db.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "passengers")

public class Passengers {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "surname")
    private String surname;

    @ColumnInfo(name = "initials")
    private String initials;

    @ColumnInfo(name = "ticket_number")
    private int ticketNumber;


    public Passengers(String surname, String initials, int ticketNumber) {
        this.surname = surname;
        this.initials = initials;
        this.ticketNumber = ticketNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getInitials() {
        return initials;
    }

    public void setInitials(String initials) {
        this.initials = initials;
    }

    public int getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketNumber(int ticketNumber) {
        this.ticketNumber = ticketNumber;
    }
}
