package com.example.ambareeshb.payukickstarter.DI;

import android.app.Application;
import android.support.v4.app.FragmentManager;

import com.example.ambareeshb.payukickstarter.Api.ApiInterface;
import com.example.ambareeshb.payukickstarter.DI.modules.ActivityModule;
import com.example.ambareeshb.payukickstarter.DI.modules.ApplicationModule;
import com.example.ambareeshb.payukickstarter.DI.modules.DatabaseModule;
import com.example.ambareeshb.payukickstarter.DI.modules.NetworkModule;
import com.example.ambareeshb.payukickstarter.database.ProjectsDao;
import com.example.ambareeshb.payukickstarter.repositories.ProjectsRepository;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by ambareeshb on 16/08/17.
 */
 @Component(modules = {ApplicationModule.class, NetworkModule.class,
         DatabaseModule.class, ActivityModule.class})
 @Singleton
public interface ApplicationComponent {
    Application application();
    ApiInterface apiInterface();
    ProjectsDao projectDao();
    ProjectsRepository projectsRepository();
    FragmentManager fragmentManager();
}
