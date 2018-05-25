package by.belzhd.android.tickectchecker.db.dao.report;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import by.belzhd.android.tickectchecker.db.entities.report.ExitReason;

@Dao
public interface ExitReasonDao {
    @Insert
    void insert(ExitReason exitReason);

    @Insert
    void insertAll(ExitReason... exitReasons);

    @Update
    void update(ExitReason exitReason);

    @Delete
    void delete(ExitReason exitReason);

    @Delete
    void deleteAll(ExitReason... exitReasons);

    @Query("SELECT * FROM exit_reason")
    List<ExitReason> getAll();

    @Query("SELECT * FROM exit_reason WHERE rep_passenger=:repPassenger")
    ExitReason getExitReasongByPassenger(int repPassenger);
}
