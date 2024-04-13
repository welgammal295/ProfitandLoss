/**
 * Name: Walid Elgammal
 * Date: 04/13/2024
 * Explanation: Part 02 Login and Landing Page
 * Persistent storage -  Room database wrapper
 * SharedPreferences - two Predefined Users
 * Check Youtube Video
 */

package com.welgammal.walid.profitandloss.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.welgammal.walid.profitandloss.database.entities.User;

import java.util.List;


@Dao
public interface UserDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert (User...user);

    @Delete
    void delete (User user);

    @Query("SELECT * FROM " + ProfitLossDB.USER_TABLE + " ORDER BY username")
    LiveData<List<User>> getAllUsers();


    @Query("DELETE FROM "+ ProfitLossDB.USER_TABLE )
    void deleteAll();

    @Query("SELECT * FROM " + ProfitLossDB.USER_TABLE + " WHERE username == :username")
    LiveData<User> getUserByUserName(String username);

    @Query("SELECT * FROM " + ProfitLossDB.USER_TABLE + " WHERE id == :userId")
    LiveData<User> getUserByUserId(int userId);
}
