package by.belzhd.android.tickectchecker.db.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "conductor", foreignKeys = @ForeignKey(entity = Route.class,
                                                            parentColumns = "id",
                                                            childColumns = "route_id",
                                                            onDelete = ForeignKey.CASCADE))
public class Conductor {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "surname")
    private String surname;

    @ColumnInfo(name = "initials")
    private String initials;

    @ColumnInfo(name = "route_id")
    private int routeId;


    public Conductor(String surname, String initials, int routeId) {
        this.surname = surname;
        this.initials = initials;
        this.routeId = routeId;
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

    public int getRouteId() {
        return routeId;
    }

    public void setRouteId(int routeId) {
        this.routeId = routeId;
    }
}
