package by.belzhd.android.tickectchecker.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import by.belzhd.android.tickectchecker.db.entities.Cariage;
import by.belzhd.android.tickectchecker.db.entities.Conductor;
import by.belzhd.android.tickectchecker.db.entities.Passengers;
import by.belzhd.android.tickectchecker.db.entities.PassengersStatus;
import by.belzhd.android.tickectchecker.db.entities.Route;
import by.belzhd.android.tickectchecker.db.entities.Seat;
import by.belzhd.android.tickectchecker.db.entities.StationCode;
import by.belzhd.android.tickectchecker.db.entities.Train;
import by.belzhd.android.tickectchecker.db.dao.CariageDao;
import by.belzhd.android.tickectchecker.db.dao.ConductorDao;
import by.belzhd.android.tickectchecker.db.dao.PassengersDao;
import by.belzhd.android.tickectchecker.db.dao.PassengersStatusDao;
import by.belzhd.android.tickectchecker.db.dao.RouteDao;
import by.belzhd.android.tickectchecker.db.dao.SeatDao;
import by.belzhd.android.tickectchecker.db.dao.StationCodeDao;
import by.belzhd.android.tickectchecker.db.dao.TrainDao;


@Database(entities = {Cariage.class, Conductor.class, Passengers.class, PassengersStatus.class,
        Route.class, Seat.class, StationCode.class, Train.class}, version = 1)
public abstract class GeneralAppDatabase extends RoomDatabase {
    public abstract CariageDao CariageDao();
    public abstract ConductorDao ConductorDao();
    public abstract PassengersDao PassengersDao();
    public abstract PassengersStatusDao PassengersStatusDao();
    public abstract RouteDao RouteDao();
    public abstract SeatDao SeatDao();
    public abstract StationCodeDao StationCodeDao();
    public abstract TrainDao TrainDao();

}
