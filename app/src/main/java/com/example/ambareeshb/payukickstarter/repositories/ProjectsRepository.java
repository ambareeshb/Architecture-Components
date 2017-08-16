package com.example.ambareeshb.payukickstarter.repositories;

import android.arch.lifecycle.LiveData;
import android.util.Log;

import com.example.ambareeshb.payukickstarter.Api.ApiInterface;
import com.example.ambareeshb.payukickstarter.database.Project;
import com.example.ambareeshb.payukickstarter.database.ProjectsDao;
import com.example.ambareeshb.payukickstarter.helpers.RetrofitHelper;

import java.util.List;


import javax.inject.Inject;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ambareeshb on 13/08/17.
 * A repository of users.
 */

public class ProjectsRepository {
    private  LiveData<List<Project>> projects;
    private ProjectsDao projectsDao;

    @Inject
    public ProjectsRepository(ProjectsDao projectDao){
        this.projectsDao = projectDao;
    }

    public LiveData<List<Project>> getProjects() {
        projects = projectsDao.getAll();
        List<Project> test = projects.getValue();
        updateProjects();
        return projects;
    }

    /**
     * Call Api to get project list
     */
    private void updateProjects() {
        ApiInterface apiInterface = RetrofitHelper.initRetrofit(ApiInterface.class);
        apiInterface.getProjects().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Project>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                    e.printStackTrace();
                    }

                    @Override
                    public void onNext(List<Project> projects) {
                        Log.d("Inserting in progress","true");

                    projectsDao.insertAll(projects);
                        Log.d("Inserting completed","true");

                    }
                });
    }



}
