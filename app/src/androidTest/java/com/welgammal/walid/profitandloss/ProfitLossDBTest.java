
/** The purpose of this class is use In-Memory database
 * to test Room SQLite
 */

package com.welgammal.walid.profitandloss;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.welgammal.walid.profitandloss.database.entities.User;

import junit.framework.TestCase;

import org.junit.runner.RunWith;


@RunWith(AndroidJUnit4.class)
public class ProfitLossDBTest extends TestCase {

    private User userDao;
    private ProfitLossDBTest db;

//    @Before
//    public void createDb() {
//        Context context = ApplicationProvider.getApplicationContext();
//        db = Room.inMemoryDatabaseBuilder(context, ProfitLossDBTest.class).build();
//        userDao = db.getUser();
//    }
//
//    private User getUser() {
//    }
//
//    @After
//    public void closeDb() throws IOException {
//        db.close();
//    }
//
//    @Test
//    public void writeUserAndReadInList() throws Exception {
//        User user = TestUtil.createUser(3);
//        user.setName("george");
//        userDao.insert(user);
//        List<User> byName = userDao.findUsersByName("george");
//        assertThat(byName.get(0), equalTo(user));
//    }
}