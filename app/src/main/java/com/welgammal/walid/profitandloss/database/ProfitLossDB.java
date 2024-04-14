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
import com.welgammal.walid.profitandloss.database.entities.User;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Elements.class, User.class}, version = 3, exportSchema = false)
public abstract class ProfitLossDB extends RoomDatabase {

    public static final String USER_TABLE = "user_table";
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
    /** Predefined users declared and initialized */
    private static final RoomDatabase.Callback addDefaultValues = new RoomDatabase.Callback(){

        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            Log.i(MainActivity.TAG, "DATABASE CREATED!");
            databaseWriteExecutor.execute(() -> {
                UserDAO dao = INSTANCE.userDAO();
                dao.deleteAll();
                User admin = new User("admin2", "admin2");
                admin.setAdmin(true);
                dao.insert(admin);

                User testUser1 = new User("testuser1", "testuser1");
                dao.insert(testUser1);
            });
        }

    };

    public abstract ProfitLossDAO profitLossDAO();

    public abstract UserDAO userDAO();

}
