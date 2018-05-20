package by.belzhd.android.tickectchecker.db.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "cariage", foreignKeys = @ForeignKey(entity = Train.class,
                                                         parentColumns = "id",
                                                         childColumns = "train_id",
                                                         onDelete = ForeignKey.CASCADE))
public class Cariage {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "description")
    private String description;

    @ColumnInfo(name = "number")
    private int number;

    @ColumnInfo(name = "train_id")
    private int trainId;


    public Cariage(String description, int number, int trainId) {
        this.description = description;
        this.number = number;
        this.trainId = trainId;
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

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getTrainId() {
        return trainId;
    }

    public void setTrainId(int trainId) {
        this.trainId = trainId;
    }
}

