package by.belzhd.android.tickectchecker.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import by.belzhd.android.tickectchecker.db.dao.report.ExitReasonDao;
import by.belzhd.android.tickectchecker.db.dao.report.ReportPassengerDao;
import by.belzhd.android.tickectchecker.db.dao.report.ReportRouteDao;
import by.belzhd.android.tickectchecker.db.dao.report.ReportStationDao;
import by.belzhd.android.tickectchecker.db.entities.report.ExitReason;
import by.belzhd.android.tickectchecker.db.entities.report.ReportPassengers;
import by.belzhd.android.tickectchecker.db.entities.report.ReportRoute;
import by.belzhd.android.tickectchecker.db.entities.report.ReportStation;

@Database(entities = {ExitReason.class, ReportPassengers.class, ReportStation.class, ReportRoute.class}, version = 1)
public abstract class ReportsAppDatabase extends RoomDatabase {
    public abstract ExitReasonDao exitReasonDao();
    public abstract ReportPassengerDao reportPassengerDao();
    public abstract ReportStationDao reportStationDao();
    public abstract ReportRouteDao reportRouteDao();
}
