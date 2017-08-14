package com.example.ambareeshb.payukickstarter;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.example.ambareeshb.payukickstarter.database.AppDatabase;

/**
 * Created by ambareeshb on 13/08/17.
 */

public class App extends Application {
    private AppDatabase mDb;
    @Override
    public void onCreate() {
        super.onCreate();
        mDb = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "AppDatabase").allowMainThreadQueries().build();

    }

    public AppDatabase getmDb() {
        return mDb;
    }
}
