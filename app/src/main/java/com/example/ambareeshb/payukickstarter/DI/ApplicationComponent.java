package com.example.ambareeshb.payukickstarter.DI;

import android.app.Application;

import com.example.ambareeshb.payukickstarter.Api.ApiInterface;
import com.example.ambareeshb.payukickstarter.DI.modules.ApplicationModule;
import com.example.ambareeshb.payukickstarter.DI.modules.DatabaseModule;
import com.example.ambareeshb.payukickstarter.DI.modules.NetworkModule;
import com.example.ambareeshb.payukickstarter.database.AppDatabase;
import com.example.ambareeshb.payukickstarter.database.daos.ProjectsDao;
import com.example.ambareeshb.payukickstarter.repositories.ProjectsRepository;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by ambareeshb on 16/08/17.
 */
 @Component(modules = {ApplicationModule.class, NetworkModule.class,
         DatabaseModule.class})
 @Singleton
public interface ApplicationComponent {
    Application application();
    ApiInterface apiInterface();
    AppDatabase database();
    ProjectsRepository projectsRepository();
}
