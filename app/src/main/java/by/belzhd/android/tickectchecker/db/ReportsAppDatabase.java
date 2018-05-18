package by.belzhd.android.tickectchecker.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import by.belzhd.android.tickectchecker.db.dao.ExitReasonDao;
import by.belzhd.android.tickectchecker.db.dao.ReportPassengerDao;
import by.belzhd.android.tickectchecker.db.dao.ReportRouteDao;
import by.belzhd.android.tickectchecker.db.dao.ReportStationDao;
import by.belzhd.android.tickectchecker.db.entities.ExitReason;
import by.belzhd.android.tickectchecker.db.entities.ReportPassengers;
import by.belzhd.android.tickectchecker.db.entities.ReportRoute;
import by.belzhd.android.tickectchecker.db.entities.ReportStation;

@Database(entities = {ExitReason.class, ReportPassengers.class, ReportStation.class, ReportRoute.class}, version = 1)
public abstract class ReportsAppDatabase extends RoomDatabase {
    public abstract ExitReasonDao exitReasonDao();
    public abstract ReportPassengerDao reportPassengerDao();
    public abstract ReportStationDao reportStationDao();
    public abstract ReportRouteDao reportRouteDao();
}
