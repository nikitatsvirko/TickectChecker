package by.belzhd.android.tickectchecker.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import by.belzhd.android.tickectchecker.db.entities.ReportStation;

@Dao
public interface ReportStationDao {
    @Insert
    void insert(ReportStation reportStation);

    @Insert
    void insertAll(ReportStation... reportStation);

    @Update
    void update(ReportStation reportStation);

    @Delete
    void delete(ReportStation reportStation);

    @Delete
    void deleteAll(ReportStation... reportStation);

    @Query("SELECT * FROM report_station")
    List<ReportStation> getAll();

    @Query("SELECT * FROM report_station WHERE rep_route=:repRoute")
    List<ReportStation> getStationByRoute(int repRoute);
}
