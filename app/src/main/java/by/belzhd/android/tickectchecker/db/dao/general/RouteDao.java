package by.belzhd.android.tickectchecker.db.dao.general;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import by.belzhd.android.tickectchecker.db.entities.general.Route;

@Dao
public interface RouteDao {
    @Insert
    void insert(Route Route);

    @Insert
    void insertAll(Route... Route);

    @Update
    void update(Route Route);

    @Delete
    void delete(Route Route);

    @Delete
    void deleteAll(Route... Route);

    @Query("SELECT * FROM route")
    List<Route> getAll();

    @Query("SELECT * FROM route WHERE status=:status")
    List<Route> getByStatus(String status);

    @Query("SELECT * FROM route WHERE id=:id")
    Route getById(int id);
}

