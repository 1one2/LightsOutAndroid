package com.example.revisedandroidlightsout;

import android.app.Application;

import androidx.room.Room;

import com.example.revisedandroidlightsout.data.RankingDatabase;

public class ApplicationController extends Application {

    private static ApplicationController instance;

    private static RankingDatabase database;
    private static final String databaseName = "RankingEntriesDatabase";

    @Override
    public void onCreate()
    {
        super.onCreate();
        instance = this;
        database = Room.databaseBuilder(getApplicationContext(), RankingDatabase.class,databaseName).build();
    }

    public static RankingDatabase getDatabase()
    {
        return database;
    }

    public static ApplicationController getInstance() {
        return instance;
    }
}
