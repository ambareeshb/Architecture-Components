package com.example.ambareeshb.payukickstarter.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.example.ambareeshb.payukickstarter.App;
import com.example.ambareeshb.payukickstarter.database.Project;
import com.example.ambareeshb.payukickstarter.repositories.ProjectsRepository;

import java.util.List;

/**
 * Created by ambareeshb on 13/08/17.
 */

public class ProjectListViewModel extends AndroidViewModel {

    ProjectsRepository projects;


    public ProjectListViewModel(Application application) {
        super(application);
        projects = ((App)application).getApplicationComponent().projectsRepository();
    }

    /**
     * Get LiveData list of projects .
     */

    public LiveData<List<Project>> getProjects() {
        return projects.getProjects();
    }



}
