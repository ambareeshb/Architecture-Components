package com.example.ambareeshb.payukickstarter.repositories;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.os.AsyncTask;
import android.support.annotation.WorkerThread;
import android.util.Log;

import com.example.ambareeshb.payukickstarter.Api.ApiInterface;
import com.example.ambareeshb.payukickstarter.App;
import com.example.ambareeshb.payukickstarter.database.Project;
import com.example.ambareeshb.payukickstarter.database.ProjectsDao;
import com.example.ambareeshb.payukickstarter.resources.Resource;

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

    private ProjectsDao projectsDao;
    private LiveData<List<Project>> projects;

    @Inject
    public ProjectsRepository(ProjectsDao projectDao) {
        this.projectsDao = projectDao;
    }

    public LiveData<List<Project>> getProjects() {
        projects = projectsDao.getAll();
        if(shouldFetch(projects)) updateProjects();
        return projects;
    }

    /**
     * Whether to fetch data from DB.
     *
     * @param oldData
     * @return
     */
    private boolean shouldFetch(LiveData<List<Project>> oldData) {
        return true;
    }

    /**
     * Call Api to get project list
     */
    private void updateProjects() {
        ApiInterface apiInterface = App.getApplicationComponent()
                .apiInterface();
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
                        Log.d("Inserting ", "in progress");
                        new DatabaseJob().execute(projects);
                    }
                });
    }

    /**
     * Insert list of projects into DB.
     *
     * @param projects to insert.
     */
    @WorkerThread
    private int insertIntoDataBase(List<Project> projects) {
        return projectsDao.insertAll(projects).length;
    }

    /**
     * Asynchronous job for database insertion.
     */
    private class DatabaseJob extends AsyncTask<List<Project>, Integer, Integer> {

        @Override
        protected Integer doInBackground(List<Project>... params) {
            return insertIntoDataBase(params[0]);
        }

        @Override
        protected void onPostExecute(Integer length) {
            super.onPostExecute(length);
            Log.d("Inserted count", " " + length);
        }
    }

}
