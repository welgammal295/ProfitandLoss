package com.welgammal.walid.profitandloss.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.welgammal.walid.profitandloss.database.entities.Elements;

import java.util.List;

@Dao
public interface ProfitLossDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Elements element);

    @Query("SELECT * FROM " + ProfitLossDB.ELEMENTS_TABLE)
    List<Elements> getAllRecords();

}
