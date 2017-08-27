package com.example.ambareeshb.payukickstarter.DaggerTest;

import com.example.ambareeshb.payukickstarter.Api.ApiInterface;
import com.example.ambareeshb.payukickstarter.DI.ApplicationComponent;
import com.example.ambareeshb.payukickstarter.DI.modules.NetworkModule;
import com.example.ambareeshb.payukickstarter.database.AppDatabase;
import com.example.ambareeshb.payukickstarter.database.daos.ProjectsDao;
import com.example.ambareeshb.payukickstarter.repositories.ProjectsRepository;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by ambareeshb on 27/08/17.
 */

@Component(modules = {DatabaseModule.class, NetworkModule.class})
@Singleton
public interface ApplicationComponentTest {
    ApiInterface apiInterface();
    AppDatabase database();
    ProjectsDao projectDao();
    ProjectsRepository projectsRepository();

}
