package com.welgammal.walid.profitandloss.Database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.welgammal.walid.profitandloss.Database.entities.Elements;

import java.util.ArrayList;

@Dao
public interface ProfitLossDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Elements element);

    @Query("SELECT * FROM " + ProfitLossDB.ELEMENTS_TABLE)
    ArrayList<Elements> getAllRecords();

}
