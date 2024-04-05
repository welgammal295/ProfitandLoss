package com.welgammal.walid.profitandloss.database;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.welgammal.walid.profitandloss.database.entities.Elements;
import com.welgammal.walid.profitandloss.MainActivity;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Elements.class}, version = 1, exportSchema = false)
public abstract class ProfitLossDB extends RoomDatabase {

    private static final String DATABASE_NAME = "ProfitLoss_database";
    public static final String ELEMENTS_TABLE = "elementsTable";

    private static volatile ProfitLossDB INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;

    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static ProfitLossDB getDatabase(final Context context) {
        if (INSTANCE == null){
            synchronized (ProfitLossDAO.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                                    ProfitLossDB.class,
                                        DATABASE_NAME
                            )
                            .fallbackToDestructiveMigration()
                            .addCallback(addDefaultValues)
                            .build();
                }
            }
        }
        return INSTANCE;
    }
    private static final RoomDatabase.Callback addDefaultValues = new RoomDatabase.Callback(){

        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db){
            super.onCreate(db);
            Log.i(MainActivity.TAG, "DATABASE CREATED!");
        }

    };

    public abstract ProfitLossDAO profitLossDAO();

}
