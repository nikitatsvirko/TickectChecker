package by.belzhd.android.tickectchecker.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import by.belzhd.android.tickectchecker.db.entities.PassengersStatus;

@Dao
public interface PassengersStatusDao {
    @Insert
    void insert(PassengersStatus PassengersStatus);

    @Insert
    void insertAll(PassengersStatus... PassengersStatus);

    @Update
    void update(PassengersStatus PassengersStatus);

    @Delete
    void delete(PassengersStatus PassengersStatus);

    @Delete
    void deleteAll(PassengersStatus... PassengersStatus);

    @Query("SELECT * FROM passengers_status")
    List<PassengersStatus> getAll();

}
