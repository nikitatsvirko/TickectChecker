package by.belzhd.android.tickectchecker.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import by.belzhd.android.tickectchecker.db.entities.Train;

@Dao
public interface TrainDao {
    @Insert
    void insert(Train Train);

    @Insert
    void insertAll(Train... Train);

    @Update
    void update(Train Train);

    @Delete
    void delete(Train Train);

    @Delete
    void deleteAll(Train... Train);

    @Query("SELECT * FROM train")
    List<Train> getAll();

}

