package com.example.ambareeshb.payukickstarter.DI.modules;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.example.ambareeshb.payukickstarter.database.AppDatabase;
import com.example.ambareeshb.payukickstarter.database.ProjectsDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ambareeshb on 16/08/17.
 */
@Module
public class DatabaseModule {
    @Provides
    @Singleton
    ProjectsDao provideProjectsDao(AppDatabase db){
        return db.projectsDao();
    }
    @Provides
    @Singleton
    AppDatabase provideDatabase(Application app){
       return Room.databaseBuilder(app,
                AppDatabase.class, "AppDatabase").allowMainThreadQueries().build();
    }
}
