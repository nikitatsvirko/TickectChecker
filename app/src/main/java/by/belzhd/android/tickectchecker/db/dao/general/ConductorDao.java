package by.belzhd.android.tickectchecker.db.dao.general;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import by.belzhd.android.tickectchecker.db.entities.general.Conductor;

@Dao
public interface ConductorDao {
    @Insert
    void insert(Conductor Conductor);

    @Insert
    void insertAll(Conductor... Conductor);

    @Update
    void update(Conductor Conductor);

    @Delete
    void delete(Conductor Conductor);

    @Delete
    void deleteAll(Conductor... Conductor);

    @Query("SELECT * FROM conductor")
    List<Conductor> getAll();

}

