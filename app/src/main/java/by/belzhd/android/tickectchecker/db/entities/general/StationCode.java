package by.belzhd.android.tickectchecker.db.entities.general;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "station_code")

public class StationCode {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "code")
    private int code;

    @ColumnInfo(name = "description")
    private String description;

    public StationCode(String description, int code) {
        this.code = code;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int id) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}

