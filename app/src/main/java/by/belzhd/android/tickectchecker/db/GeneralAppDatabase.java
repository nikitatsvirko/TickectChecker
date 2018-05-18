package by.belzhd.android.tickectchecker.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import by.belzhd.android.tickectchecker.db.entities.ExitReason;
import by.belzhd.android.tickectchecker.db.entities.ReportRoute;

@Database(entities = {ReportRoute.class}, version = 1)
public abstract class GeneralAppDatabase extends RoomDatabase {

}
