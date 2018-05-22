package by.belzhd.android.tickectchecker.db.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "seat", foreignKeys = @ForeignKey(entity = Cariage.class,
                                                      parentColumns = "id",
                                                      childColumns = "cariage_id",
                                                      onDelete = ForeignKey.CASCADE))
public class Seat {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "number")
    private int number;

    @ColumnInfo(name = "cariage_id")
    private int cariageId;


    public Seat(int number, int cariageId) {
        this.number = number;
        this.cariageId = cariageId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getCariageId() {
        return cariageId;
    }

    public void setCariageId(int cariageId) {
        this.cariageId = cariageId;
    }
}


