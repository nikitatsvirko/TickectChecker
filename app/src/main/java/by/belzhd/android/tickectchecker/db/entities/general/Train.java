package by.belzhd.android.tickectchecker.db.entities.general;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "train")

public class Train {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "train_number")
    private String trainNumber;

    public Train(String trainNumber) {
        this.trainNumber = trainNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTrainNumber() {
        return trainNumber;
    }

    public void setTrainNumber(String trainNumber) {
        this.trainNumber = trainNumber;
    }

}


