package by.belzhd.android.tickectchecker.db.dao.general;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import by.belzhd.android.tickectchecker.db.entities.general.Passengers;

@Dao
public interface PassengersDao {
    @Insert
    void insert(Passengers Passengers);

    @Insert
    void insertAll(Passengers... Passengers);

    @Update
    void update(Passengers Passengers);

    @Delete
    void delete(Passengers Passengers);

    @Delete
    void deleteAll(Passengers... Passengers);

    @Query("SELECT * FROM passengers")
    List<Passengers> getAll();

}

