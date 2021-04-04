package com.trainersstocks.CustomersApp.abstractactivity;

import android.app.Application;
import android.content.Context;

import com.trainersstocks.CustomersApp.Utils.PreferenceManger;


public class MyApp extends Application {


    private static MyApp myApp;

    @Override
    public void onCreate() {
        super.onCreate();
        PreferenceManger.initPreference(this);
        myApp = this;
    }


    public static Context getAppContext() {
        return myApp;
    }

}
