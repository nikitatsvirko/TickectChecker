package by.belzhd.android.tickectchecker.db.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "report_route")
public class ReportRoute {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "start_date")
    private int startDate;

    @ColumnInfo(name = "train_number")
    private String trainNumber;

    @ColumnInfo(name = "start_station")
    private String startStation;

    @ColumnInfo(name = "end_station")
    private String endStation;

    @ColumnInfo(name = "status")
    private String status;

    public ReportRoute(int startDate, String trainNumber, String startStation, String endStation, String status) {
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

    public String getTrainNumber() {
        return trainNumber;
    }

    public void setTrainNumber(String trainNumber) {
        this.trainNumber = trainNumber;
    }

    public String getStartStation() {
        return startStation;
    }

    public void setStartStation(String startStation) {
        this.startStation = startStation;
    }

    public String getEndStation() {
        return endStation;
    }

    public void setEndStation(String endStation) {
        this.endStation = endStation;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
