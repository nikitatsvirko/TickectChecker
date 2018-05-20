package by.belzhd.android.tickectchecker.db.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "station_code")

public class StationCode {
    @PrimaryKey(autoGenerate = true) //тут не автогенер наверное (id - код станции(6-ти знач число)
                                    //хотя эта таблица изначально есть в прилаге,поэтому мб и пох
    private int id;

    @ColumnInfo(name = "description")
    private String description;

    public StationCode(String description) {
        this.description = description;
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

}

