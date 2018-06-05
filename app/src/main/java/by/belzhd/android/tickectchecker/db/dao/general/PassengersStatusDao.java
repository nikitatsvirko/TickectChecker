package by.belzhd.android.tickectchecker.db.dao.general;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import by.belzhd.android.tickectchecker.db.entities.general.PassengersStatus;

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

    @Query("SELECT * FROM passengers_status WHERE route_id=:id")
    List<PassengersStatus> getByRouteId(int id);

    @Query("SELECT * FROM passengers_status WHERE route_id=:id AND status=:status")
    List<PassengersStatus> getByRouteIdAndStatus(int id, String status);

    @Query("SELECT * FROM passengers_status WHERE passenger=:passengerId")
    PassengersStatus getByPassengerId(int passengerId);

    @Query("SELECT * FROM passengers_status WHERE seat_id=:seatId AND route_id=:routeId " +
            "AND entry_station=:entryStationId AND status=:status")
    PassengersStatus getPassengerStatusBy(int seatId, int routeId, int entryStationId, String status);
}

