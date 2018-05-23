package by.belzhd.android.tickectchecker.db.dao.report;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import by.belzhd.android.tickectchecker.db.entities.report.ReportRoute;

@Dao
public interface ReportRouteDao {
    @Insert
    void insert(ReportRoute reportRoute);

    @Insert
    void insertAll(ReportRoute... reportRoute);

    @Update
    void update(ReportRoute reportRoute);

    @Delete
    void delete(ReportRoute reportRoute);

    @Delete
    void deleteAll(ReportRoute... reportRoute);

    @Query("SELECT * FROM report_route")
    List<ReportRoute> getAll();

    @Query("SELECT * FROM report_route WHERE start_date=:startDate")
    List<ReportRoute> getRouteByStartDate(int startDate);

    @Query("SELECT * FROM report_route WHERE train_number=:trainNumber")
    List<ReportRoute> getRouteByTrainNumber(String trainNumber);
}
