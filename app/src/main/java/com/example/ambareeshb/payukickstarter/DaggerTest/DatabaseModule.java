package com.example.ambareeshb.payukickstarter.DaggerTest;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.Context;

import com.example.ambareeshb.payukickstarter.DI.modules.ApplicationModule;
import com.example.ambareeshb.payukickstarter.database.AppDatabase;
import com.example.ambareeshb.payukickstarter.database.daos.ProjectsDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ambareeshb on 27/08/17.
 */
@Module()
public class DatabaseModule {
    private Context context;

    public DatabaseModule(Context context){
        this.context = context;
    }

    @Provides
    @Singleton
    AppDatabase provideTestDataBase(){
        return Room.inMemoryDatabaseBuilder(context,AppDatabase.class).allowMainThreadQueries().build();
    }

    @Provides
    @Singleton
    ProjectsDao provideProjectsDao(AppDatabase db){
        return db.projectsDao();
    }

}
