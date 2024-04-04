package com.welgammal.walid.profitandloss.Database;

import android.app.Application;
import android.util.Log;

import com.welgammal.walid.profitandloss.Database.entities.Elements;
import com.welgammal.walid.profitandloss.MainActivity;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class ProfitLossRepository {
    private ProfitLossDAO profitLossDAO;
    private ArrayList<Elements> allogs;

    public ProfitLossRepository(Application application){
        ProfitLossDB db = ProfitLossDB.getDatabase(application);
        this.profitLossDAO = db.profitLossDAO();
        this.allogs = this.profitLossDAO.getAllRecords();

    }
    public ArrayList<Elements> getAllogs(){
        Future<ArrayList<Elements>> future = ProfitLossDB.databaseWriteExecutor.submit(
                new Callable<ArrayList<Elements>>() {
                    @Override
                    public ArrayList<Elements> call() throws Exception {
                        return profitLossDAO.getAllRecords();
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
