package by.belzhd.android.tickectchecker.db.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "report_passengers", foreignKeys = @ForeignKey(entity = ReportStation.class,
                                                                   parentColumns = "id",
                                                                   childColumns = "rep_station",
                                                                   onDelete = ForeignKey.CASCADE))
public class ReportPassengers {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "surname")
    private String surname;

    @ColumnInfo(name = "initials")
    private String initials;

    @ColumnInfo(name = "rep_station")
    private int repStation;

    @ColumnInfo(name = "ticket_number")
    private int ticketNumber;

    @ColumnInfo(name = "status")
    private String status;

    public ReportPassengers(String surname, String initials, int repStation, int ticketNumber, String status) {
        this.surname = surname;
        this.initials = initials;
        this.repStation = repStation;
        this.ticketNumber = ticketNumber;
        this.status = status;
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

    public int getRepStation() {
        return repStation;
    }

    public void setRepStation(int repStation) {
        this.repStation = repStation;
    }

    public int getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketNumber(int ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
