package by.belzhd.android.tickectchecker.db.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "passengers_status", foreignKey1 = @ForeignKey(entity = Seat.class,
                                                                    parentColumns = "id",
                                                                    childColumns = "seat_id",
                                                                    onDelete = ForeignKey.CASCADE),
                                                                    foreignKey2 = @ForeignKey(entity = Route.class,
                                                                            parentColumns = "id",
                                                                            childColumns = "route_id",
                                                                            onDelete = ForeignKey.CASCADE),
                                                                    foreignKey3 = @ForeignKey(entity = StationCode.class,
                                                                            parentColumns = "id",
                                                                            childColumns = "entry_station",
                                                                            onDelete = ForeignKey.CASCADE),
                                                                    foreignKey4 = @ForeignKey(entity = StationCode.class,
                                                                            parentColumns = "id",
                                                                            childColumns = "exit_station",
                                                                            onDelete = ForeignKey.CASCADE),
                                                                    foreignKey5 = @ForeignKey(entity = Passengers.class,
                                                                            parentColumns = "id",
                                                                            childColumns = "passenger",
                                                                            onDelete = ForeignKey.CASCADE))

//хз как несколько ключей задавать, поэтому так))
public class PassengersStatus {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "seat_id")
    private int seatId;

    @ColumnInfo(name = "route_id")
    private int routeId;

    @ColumnInfo(name = "entry_station")
    private int entryStation;

    @ColumnInfo(name = "exit_station")
    private int exitStation;

    @ColumnInfo(name = "passenger")
    private int passenger;

    @ColumnInfo(name = "status")
    private String status;

    public PassengersStatus(int seatId, int routeId, int entryStation, int exitStation, int passenger, String status) {
        this.seatId = seatId;
        this.routeId = routeId;
        this.entryStation = entryStation;
        this.exitStation = exitStation;
        this.passenger = passenger;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRouteId() {
        return routeId;
    }

    public void setRouteId(int routeId) {
        this.routeId = routeId;
    }

    public int getSeatId() {
        return seatId;
    }

    public void setSeatId(int seatId) {
        this.seatId = seatId;
    }

    public int getEntryStation() {
        return entryStation;
    }

    public void setEntryStation(int entryStation) {
        this.entryStation = entryStation;
    }

    public int getExitStation() {
        return exitStation;
    }

    public void setExitStation(int exitStation) {
        this.exitStation = exitStation;
    }

    public int getPassenger() {
        return passenger;
    }

    public void setPassenger(int passenger) {
        this.passenger = passenger;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
