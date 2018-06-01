package by.belzhd.android.tickectchecker.db.dao.general;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import by.belzhd.android.tickectchecker.db.entities.general.StationCode;

@Dao
public interface StationCodeDao {
    @Insert
    void insert(StationCode StationCode);

    @Insert
    void insertAll(StationCode... StationCode);

    @Update
    void update(StationCode StationCode);

    @Delete
    void delete(StationCode StationCode);

    @Delete
    void deleteAll(StationCode... StationCode);

    @Query("SELECT * FROM station_code")
    List<StationCode> getAll();

    @Query("SELECT * FROM station_code WHERE id=:id")
    StationCode getStationCodeById(int id);
}

