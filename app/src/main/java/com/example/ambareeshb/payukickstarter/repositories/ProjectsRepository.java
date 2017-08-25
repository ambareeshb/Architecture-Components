package com.example.ambareeshb.payukickstarter.repositories;

import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.support.annotation.WorkerThread;
import android.util.Log;

import com.example.ambareeshb.payukickstarter.Api.ApiInterface;
import com.example.ambareeshb.payukickstarter.App;
import com.example.ambareeshb.payukickstarter.Constants;
import com.example.ambareeshb.payukickstarter.database.enitities.Project;
import com.example.ambareeshb.payukickstarter.database.daos.ProjectsDao;

import java.util.Date;
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
    private static String LOG_TAG = "Project Repo";

    private ProjectsDao projectsDao;
    private LiveData<List<Project>> projects;


    @Inject
    public ProjectsRepository(ProjectsDao projectDao) {
        this.projectsDao = projectDao;
    }


    /**
     * Live data of projects.
     *
     * @return
     */
    public LiveData<List<Project>> getProjects() {
        projects = projectsDao.getAll();
        fetchProjects();
        return projects;
    }

    /**
     * Get project from network.
     */
    public void fetchProjects() {
        Log.d(LOG_TAG, "Fetching projects");
        if (shouldFetch(projects)) updateProjects();
    }

    /**
     * Whether to fetch data from DB.
     *
     * @param oldData oldData from DB
     * @return to retrieve data from network.
     */
    private boolean shouldFetch(LiveData<List<Project>> oldData) {
        Date timeStamp = new Date(0);
        if(oldData == null || oldData.getValue() == null) return true;

        for (Project project : oldData.getValue()) {
            timeStamp = (project.getTimeStamp().compareTo(timeStamp) < 0 ?
                    timeStamp : project.getTimeStamp());
        }
        Log.d(LOG_TAG, "should fetch "+((new Date().getTime() - timeStamp.getTime()) >
                Constants.NETWORK_FETCH_INTERVAL));
        return (new Date().getTime() - timeStamp.getTime()) >
                Constants.NETWORK_FETCH_INTERVAL;
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
                        Log.d(LOG_TAG, "Inserting in progress");
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
            Log.d(LOG_TAG, "Inserted count" + length);
        }
    }

}
