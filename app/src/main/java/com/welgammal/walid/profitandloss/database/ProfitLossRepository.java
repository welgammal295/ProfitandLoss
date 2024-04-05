package com.welgammal.walid.profitandloss.database;

import android.app.Application;
import android.util.Log;

import com.welgammal.walid.profitandloss.database.entities.Elements;
import com.welgammal.walid.profitandloss.MainActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class ProfitLossRepository {
    private ProfitLossDAO profitLossDAO;
    private List<Elements> allogs;

    private static ProfitLossRepository repository;
    private ProfitLossRepository(Application application){
        ProfitLossDB db = ProfitLossDB.getDatabase(application);
        this.profitLossDAO = db.profitLossDAO();
        this.allogs = (ArrayList<Elements>) this.profitLossDAO.getAllRecords();

    }

    public static ProfitLossRepository getRepository(Application application){
      if (repository != null){
            return repository;
      }

       Future<ProfitLossRepository> future = ProfitLossDB.databaseWriteExecutor.submit(
               new Callable<ProfitLossRepository>() {
           @Override
           public ProfitLossRepository call() throws Exception {
               return new ProfitLossRepository(application);
           }
       });
       try{
           return future.get();
       }catch (InterruptedException | ExecutionException e){
           Log.d(MainActivity.TAG, "Problem getting ProfitLossRepository, thread error.");
       }
       return null;

    }

    public List<Elements> getAllLogs(){
        Future<ArrayList<Elements>> future = ProfitLossDB.databaseWriteExecutor.submit(
                new Callable<ArrayList<Elements>>() {
                    @Override
                    public ArrayList<Elements> call() throws Exception {
                        return (ArrayList<Elements>) profitLossDAO.getAllRecords();
                    }
                }
        );

        try {
            return future.get();
        }catch (InterruptedException | ExecutionException e){
            e.printStackTrace();
            Log.i(MainActivity.TAG, "Problem when getting Elements in the repository");
        }
            return null;
        }
    public void insertElements(Elements element) {
        ProfitLossDB.databaseWriteExecutor.execute(() ->
        {
            profitLossDAO.insert(element);
        });
    }
}