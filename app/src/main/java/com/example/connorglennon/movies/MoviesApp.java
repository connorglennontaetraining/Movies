package com.example.connorglennon.movies;

import android.app.Application;
import android.content.Context;

/**
 * Created by Connor Glennon on 23/11/2017.
 */

public class MoviesApp extends Application{
    private static Application application;
    private static Context context;

    public static Application getApplication() {
        if(application != null) {
            return application;
        }
        return null;
    }

    public static Context getContext() {
        if(context != null) {
            return context;
        }
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        context = this;
    }
}
