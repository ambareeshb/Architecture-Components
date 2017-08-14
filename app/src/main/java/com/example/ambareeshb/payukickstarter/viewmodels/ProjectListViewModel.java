package com.example.ambareeshb.payukickstarter.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.example.ambareeshb.payukickstarter.Api.ApiInterface;
import com.example.ambareeshb.payukickstarter.database.Project;
import com.example.ambareeshb.payukickstarter.helpers.RetrofitHelper;
import com.example.ambareeshb.payukickstarter.livedata.ProjectListLiveData;

import java.util.List;

/**
 * Created by ambareeshb on 13/08/17.
 */

public class ProjectListViewModel extends AndroidViewModel {

    ProjectListLiveData projects = new ProjectListLiveData();;

    public ProjectListViewModel(Application application) {
        super(application);
    }

    /**
     * Get LiveData list of projects .
     */

    public ProjectListLiveData getProjects() {
        return projects;
    }



}
