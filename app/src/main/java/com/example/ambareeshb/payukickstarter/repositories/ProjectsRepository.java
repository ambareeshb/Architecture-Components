package com.example.ambareeshb.payukickstarter.repositories;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableInt;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
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
    private ObservableBoolean loading;

    private ProjectsDao projectsDao;
    private MediatorLiveData<List<Project>> mediatorProjects;



    @Inject
    public ProjectsRepository(ProjectsDao projectDao) {
        this.projectsDao = projectDao;
        mediatorProjects = new MediatorLiveData<>();
        loading = new ObservableBoolean();
        loading.set(false);
    }

    public ObservableBoolean getLoading() {
        return loading;
    }

    /**
     * Get project from network.
     */
    public MediatorLiveData<List<Project>> fetchProjects() {
        Log.d(LOG_TAG,"in fetch projects");
        final LiveData<List<Project>> projects = projectsDao.getAll();
        mediatorProjects.addSource(projects, new Observer<List<Project>>() {
            @Override
            public void onChanged(@Nullable List<Project> projectsList) {
                    Log.d(LOG_TAG,"Mediator live data value changed");
                    mediatorProjects.setValue(projectsList);
                    if (shouldFetch(projects)) {
                        Log.d(LOG_TAG, "starting network request");
                        loading.set(true);
                        updateProjects(App.getApplicationComponent().apiInterface());
                    }

            }
        });
        return mediatorProjects;

    }

    /**
     * Whether to fetch data from DB.
     *
     * @param oldData oldData from DB
     * @return to retrieve data from network.
     */
    public boolean shouldFetch(final LiveData<List<Project>> oldData) {
        Date timeStamp = new Date(0);
        if (oldData == null  || oldData.getValue().isEmpty()) return true;

        Log.v(LOG_TAG, "Old data value Now " + oldData.getValue());
        for (Project project : oldData.getValue()) {
            timeStamp = (project.getTimeStamp().compareTo(timeStamp) < 0 ?
                    timeStamp : project.getTimeStamp());
        }
        Log.d(LOG_TAG, "should fetch " + (new Date().getTime() - timeStamp.getTime())/1000+" s since last update");
        Log.d(LOG_TAG,"should fetch"+ ((new Date().getTime() - timeStamp.getTime()) >
                Constants.NETWORK_FETCH_INTERVAL));
        return (new Date().getTime() - timeStamp.getTime()) >
                Constants.NETWORK_FETCH_INTERVAL;
    }

    /**
     * Call Api to get project list
     */
    public void updateProjects(ApiInterface apiInterface) {
        apiInterface.getProjects()
                .subscribeOn(Schedulers.io())
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
            try {
                //Just for a horror
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return insertIntoDataBase(params[0]);
        }

        @Override
        protected void onPostExecute(Integer length) {
            super.onPostExecute(length);
            Log.d(LOG_TAG, "Inserted count" + length);
            loading.set(false);
        }
    }

}
