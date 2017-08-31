package com.example.ambareeshb.payukickstarter.DI.modules;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.example.ambareeshb.payukickstarter.DI.Qualifiers;
import com.example.ambareeshb.payukickstarter.database.AppDatabase;
import com.example.ambareeshb.payukickstarter.database.daos.ProjectsDao;
import com.example.ambareeshb.payukickstarter.repositories.ProjectsRepository;

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
                AppDatabase.class, "AppDatabase").build();
    }
    @Provides
    @Singleton
    ProjectsRepository provideRepository(ProjectsDao dao){
        return new ProjectsRepository(dao);
    }
//    @Provides
//    @Singleton
//    @Qualifiers.Named("TestDB")
//    AppDatabase provideTestDataBase(Application app){
//        return Room.inMemoryDatabaseBuilder(app,AppDatabase.class).build();
//    }
}
