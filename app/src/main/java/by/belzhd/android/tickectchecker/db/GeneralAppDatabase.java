package by.belzhd.android.tickectchecker.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import by.belzhd.android.tickectchecker.db.entities.general.Cariage;
import by.belzhd.android.tickectchecker.db.entities.general.Conductor;
import by.belzhd.android.tickectchecker.db.entities.general.Passengers;
import by.belzhd.android.tickectchecker.db.entities.general.PassengersStatus;
import by.belzhd.android.tickectchecker.db.entities.general.Route;
import by.belzhd.android.tickectchecker.db.entities.general.Seat;
import by.belzhd.android.tickectchecker.db.entities.general.StationCode;
import by.belzhd.android.tickectchecker.db.entities.general.Train;
import by.belzhd.android.tickectchecker.db.dao.general.CariageDao;
import by.belzhd.android.tickectchecker.db.dao.general.ConductorDao;
import by.belzhd.android.tickectchecker.db.dao.general.PassengersDao;
import by.belzhd.android.tickectchecker.db.dao.general.PassengersStatusDao;
import by.belzhd.android.tickectchecker.db.dao.general.RouteDao;
import by.belzhd.android.tickectchecker.db.dao.general.SeatDao;
import by.belzhd.android.tickectchecker.db.dao.general.StationCodeDao;
import by.belzhd.android.tickectchecker.db.dao.general.TrainDao;


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
