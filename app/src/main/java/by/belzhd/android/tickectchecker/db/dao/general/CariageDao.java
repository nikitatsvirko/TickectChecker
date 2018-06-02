package by.belzhd.android.tickectchecker.db.dao.general;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import by.belzhd.android.tickectchecker.db.entities.general.Cariage;

@Dao
public interface CariageDao {
    @Insert
    void insert(Cariage Cariage);

    @Insert
    void insertAll(Cariage... Cariage);

    @Update
    void update(Cariage Cariage);

    @Delete
    void delete(Cariage Cariage);

    @Delete
    void deleteAll(Cariage... Cariage);

    @Query("SELECT * FROM cariage")
    List<Cariage> getAll();

    @Query("SELECT * FROM cariage WHERE number=:number")
    Cariage getCarriageByNumber(int number);
}

