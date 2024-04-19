/** Testing Each database table must have at least three tests (insert, update, delete)*/

package com.welgammal.walid.profitandloss;

import static junit.framework.TestCase.assertEquals;

import static org.junit.Assert.assertNull;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.media3.test.utils.TestUtil;
import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.welgammal.walid.profitandloss.database.ProfitLossDB;
import com.welgammal.walid.profitandloss.database.UserDAO;
import com.welgammal.walid.profitandloss.database.entities.User;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RunWith(AndroidJUnit4.class)
public class ProfitLossDBTest {

    private UserDAO userDao;
    private ProfitLossDB db;
    private static final int NUMBER_OF_THREADS = 2;
    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    /** Use in-Memory database for testing */
    @Before
    public void createDb() {
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, ProfitLossDB.class).build();
        userDao = db.userDAO();
    }

    @After
    public void closeDb() throws IOException {
        db.close();
    }


/** Test writing a user to a database and then reading the user back from the database */
        @Test
        public void insertUserAndRead () throws Exception {

            databaseWriteExecutor.execute(() -> {
               Random random = new Random();
               int stringLength = 9;
               String randomUsername = TestUtil.buildTestString(stringLength, random);
               String password = "password";
                User user = new User(randomUsername, password);
                user.setUsername(randomUsername);
                userDao.insert(user);               //Is the new generated user inserted?
                LiveData<User> byName = userDao.getUserByUserName(randomUsername);
                User testUser = byName.getValue();
                assertEquals(testUser, user);

                userDao.delete(user);                 //Is the new generated user deleted?
                LiveData<User> deletedUser = userDao.getUserByUserName(randomUsername);
                assertNull(deletedUser.getValue());
            });




        }



}
