package com.ash.digivahan;

import android.app.Application;
import android.content.Context;

import com.ash.digivahan.utils.AppExecutors;

/**
 * Custom Application class for global app initialization.
 */
public class App extends Application {

    private static App instance;
    private AppExecutors appExecutors;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        // Initialize executors
        appExecutors = AppExecutors.getInstance();

        // TODO: Initialize other libraries here
        // e.g., FirebaseApp.initializeApp(this);
        //       OneSignal.initWithContext(this);
        //       AppDatabase.getInstance(this);
    }

    public static App getInstance() {
        return instance;
    }

    public static Context getAppContext() {
        return instance.getApplicationContext();
    }

    public AppExecutors getAppExecutors() {
        return appExecutors;
    }
}
