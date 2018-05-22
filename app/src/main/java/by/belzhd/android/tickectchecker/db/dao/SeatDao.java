package by.belzhd.android.tickectchecker.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import by.belzhd.android.tickectchecker.db.entities.Seat;

@Dao
public interface SeatDao {
    @Insert
    void insert(Seat Seat);

    @Insert
    void insertAll(Seat... Seat);

    @Update
    void update(Seat Seat);

    @Delete
    void delete(Seat Seat);

    @Delete
    void deleteAll(Seat... Seat);

    @Query("SELECT * FROM seat")
    List<Seat> getAll();

}

