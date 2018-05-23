package by.belzhd.android.tickectchecker.db.entities.report;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "report_station", foreignKeys = @ForeignKey(entity = ReportRoute.class,
                                                                parentColumns = "id",
                                                                childColumns = "rep_route",
                                                                onDelete = ForeignKey.CASCADE))
public class ReportStation {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "time_exit")
    private long timeExit;

    @ColumnInfo(name = "time_entry")
    private long timeEntry;

    @ColumnInfo(name = "station")
    private String station;

    @ColumnInfo(name = "rep_route")
    private int repRoute;

    @ColumnInfo(name = "status")
    private String status;

    public ReportStation(long timeExit, long timeEntry, String station, int repRoute, String status) {
        this.timeExit = timeExit;
        this.timeEntry = timeEntry;
        this.station = station;
        this.repRoute = repRoute;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getTimeExit() {
        return timeExit;
    }

    public void setTimeExit(long timeExit) {
        this.timeExit = timeExit;
    }

    public long getTimeEntry() {
        return timeEntry;
    }

    public void setTimeEntry(long timeEntry) {
        this.timeEntry = timeEntry;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public int getRepRoute() {
        return repRoute;
    }

    public void setRepRoute(int repRoute) {
        this.repRoute = repRoute;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
