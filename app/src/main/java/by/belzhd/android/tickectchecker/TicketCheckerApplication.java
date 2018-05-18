package by.belzhd.android.tickectchecker;

import android.app.Application;
import android.arch.persistence.room.Room;

import by.belzhd.android.tickectchecker.db.GeneralAppDatabase;
import by.belzhd.android.tickectchecker.db.ReportsAppDatabase;

import static by.belzhd.android.tickectchecker.utils.Constants.GENERAL_DB;
import static by.belzhd.android.tickectchecker.utils.Constants.REPORTS_DB;

public class TicketCheckerApplication extends Application {

    private static GeneralAppDatabase generalDB;
    private static ReportsAppDatabase reportsDB;

    @Override
    public void onCreate() {
        super.onCreate();
        generalDB = Room.databaseBuilder(this, GeneralAppDatabase.class, GENERAL_DB).allowMainThreadQueries().build();
        reportsDB = Room.databaseBuilder(this, ReportsAppDatabase.class, REPORTS_DB).allowMainThreadQueries().build();
    }

    public static GeneralAppDatabase getGeneralDB() {
        return generalDB;
    }

    public static ReportsAppDatabase getReportsDB() {
        return reportsDB;
    }
}
