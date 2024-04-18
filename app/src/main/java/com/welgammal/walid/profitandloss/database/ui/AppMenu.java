package com.welgammal.walid.profitandloss.database.ui;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import com.welgammal.walid.profitandloss.LoginActivity;
import com.welgammal.walid.profitandloss.R;

public class AppMenu {

    private static final int LOGGED_OUT = -1;
    private static final String MAIN_MENU_ACTIVITY_USER_ID = "com.welgammal.walid.profitandloss.MAIN_MENU_ACTIVITY_USER_ID";
    private static final String SHARED_PREFERENCE_USERID_KEY = "com.welgammal.walid.profitandloss.SHARED_PREFERENCE_USERID_KEY";

    private static int loggedInUserId = -1;

    public static void createMenu(MenuInflater inflater, Menu menu) {
        inflater.inflate(R.menu.logout_menu, menu);
    }

    public static void prepareMenu(Menu menu, MenuItem.OnMenuItemClickListener clickListener, String username) {
        MenuItem item = menu.findItem(R.id.logoutMenuItem);
        item.setVisible(true);
        item.setTitle(username);
        item.setOnMenuItemClickListener(clickListener);
    }

    public static void showLogoutDialog(Activity activity) {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(activity);
        final AlertDialog alertDialog = alertBuilder.create();

        alertBuilder.setMessage("Are you sure you want to log out?");
        alertBuilder.setPositiveButton("Log out?", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                logout(activity);
            }
        });
        alertBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                alertDialog.dismiss();
            }
        });

        alertBuilder.create().show();
    }

    private static void logout(Activity activity) {
        loggedInUserId = LOGGED_OUT;
        updateSharedPreference(activity);
        activity.getIntent().putExtra(MAIN_MENU_ACTIVITY_USER_ID, LOGGED_OUT);
        Intent intent = new Intent(activity, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
        activity.finish();
    }

    private static void updateSharedPreference(Activity activity) {
        SharedPreferences sharedPreferences = activity.getSharedPreferences(activity.getString(R.string.sharedprefrence_file_key), Context.MODE_PRIVATE);
        SharedPreferences.Editor sharedPrefEditor = sharedPreferences.edit();
        sharedPrefEditor.putInt(activity.getString(R.string.preference_userId_key), loggedInUserId);
        sharedPrefEditor.apply();
        activity.getIntent().putExtra(MAIN_MENU_ACTIVITY_USER_ID, LOGGED_OUT);
    }

    public static void setLoggedInUserId(int userId) {
        loggedInUserId = userId;
    }

    public static int getLoggedInUserId() {
        return loggedInUserId;
    }
}