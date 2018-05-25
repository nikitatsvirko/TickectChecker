package by.belzhd.android.tickectchecker.db.dao.report;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import by.belzhd.android.tickectchecker.db.entities.report.ReportPassengers;

@Dao
public interface ReportPassengerDao {
    @Insert
    void insert(ReportPassengers reportPassengers);

    @Insert
    void insertAll(ReportPassengers... reportPassengers);

    @Update
    void update(ReportPassengers reportPassengers);

    @Delete
    void delete(ReportPassengers reportPassengers);

    @Delete
    void deleteAll(ReportPassengers... reportPassengers);

    @Query("SELECT * FROM report_passengers")
    List<ReportPassengers> getAll();

    @Query("SELECT * FROM report_passengers WHERE rep_station=:repStation")
    List<ReportPassengers> getPassengersByStation(int repStation);

    @Query("SELECT * FROM report_passengers WHERE ticket_number=:ticketNumber")
    ReportPassengers getPassengerByTicket(int ticketNumber);
}
