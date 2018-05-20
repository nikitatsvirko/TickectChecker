package by.belzhd.android.tickectchecker.db.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "route", foreignKeys = {@ForeignKey(entity = StationCode.class,
                                                                parentColumns = "id",
                                                                childColumns = "start_station",
                                                                onDelete = ForeignKey.CASCADE),
                                            @ForeignKey(entity = StationCode.class,
                                                                parentColumns = "id",
                                                                childColumns = "end_station",
                                                                onDelete = ForeignKey.CASCADE),
                                            @ForeignKey(entity = Train.class,
                                                                parentColumns = "id",
                                                                childColumns = "train_number",
                                                                onDelete = ForeignKey.CASCADE)})


public class Route {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "start_date")
    private int startDate;

    @ColumnInfo(name = "train_number")
    private int trainNumber;

    @ColumnInfo(name = "start_station")
    private int startStation;

    @ColumnInfo(name = "end_station")
    private int endStation;

    @ColumnInfo(name = "status")
    private String status;

    public Route(int startDate, int trainNumber, int startStation, int endStation, String status) {
        this.startDate = startDate;
        this.trainNumber = trainNumber;
        this.startStation = startStation;
        this.endStation = endStation;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStartDate() {
        return startDate;
    }

    public void setStartDate(int startDate) {
        this.startDate = startDate;
    }

    public int getTrainNumber() {
        return trainNumber;
    }

    public void setTrainNumber(int trainNumber) {
        this.trainNumber = trainNumber;
    }

    public int getStartStation() {
        return startStation;
    }

    public void setStartStation(int startStation) {
        this.startStation = startStation;
    }

    public int getEndStation() {
        return endStation;
    }

    public void setEndStation(int endStation) {
        this.endStation = endStation;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
