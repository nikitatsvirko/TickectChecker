package by.belzhd.android.tickectchecker.db.entities.report;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "exit_reason", foreignKeys = @ForeignKey(entity = ReportPassengers.class,
                                                             parentColumns = "id",
                                                             childColumns = "rep_passenger",
                                                             onDelete = ForeignKey.CASCADE))
public class ExitReason {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "description")
    private String description;

    @ColumnInfo(name = "rep_passenger")
    private int repPassenger;

    public ExitReason(String description, int repPassenger) {
        this.description = description;
        this.repPassenger = repPassenger;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getRepPassenger() {
        return repPassenger;
    }

    public void setRepPassenger(int repPassenger) {
        this.repPassenger = repPassenger;
    }
}
