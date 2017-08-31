package com.example.ambareeshb.payukickstarter.repositories

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import android.databinding.ObservableBoolean
import android.os.AsyncTask
import android.support.annotation.WorkerThread
import android.util.Log
import com.example.ambareeshb.payukickstarter.Api.ApiInterface
import com.example.ambareeshb.payukickstarter.App
import com.example.ambareeshb.payukickstarter.Constants
import com.example.ambareeshb.payukickstarter.database.daos.ProjectsDao
import com.example.ambareeshb.payukickstarter.database.enitities.Project
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.util.*
import javax.inject.Inject

/**
 * Created by ambareeshb on 13/08/17.
 * A repository of users.
 */

class ProjectsRepository @Inject
constructor(private val projectsDao: ProjectsDao) {
     val loading = ObservableBoolean()
     private val mediatorProjects: MediatorLiveData<List<Project>>


    init {
        loading.set(false)
        mediatorProjects = MediatorLiveData<List<Project>>()
    }

    /**
     * Get project from network.
     */
    fun fetchProjects(): LiveData<List<Project>> {
        Log.d(LOG_TAG, "in fetch projects")
        mediatorProjects.addSource(projectsDao.all) { projectsList ->
            Log.d(LOG_TAG, "Mediator live data value changed")
            mediatorProjects.removeSource(projectsDao.all)
            mediatorProjects.value = projectsList
            if (shouldFetch(projectsList)) {
                Log.d(LOG_TAG, "starting network request")
                loading.set(true)
                updateProjects(App.applicationComponent.apiInterface())
            }
        }

        return mediatorProjects
    }

    /**
     * Whether to fetch data from DB.

     * @param oldData oldData from DB
     * *
     * @return to retrieve data from network.
     */
    fun shouldFetch(oldData: List<Project>?): Boolean {
        var timeStamp = Date(0)
        if (oldData == null || oldData.isEmpty()) return true

        Log.v(LOG_TAG, "Old data value Now " + oldData)
        for (project in oldData) {
            timeStamp = if (project.timeStamp < timeStamp)
                timeStamp
            else
                project.timeStamp
        }
        Log.d(LOG_TAG, "should fetch " + (Date().time - timeStamp.time) / 1000 + " s since last update")
        Log.d(LOG_TAG, "should fetch" + (Date().time - timeStamp.time > Constants.NETWORK_FETCH_INTERVAL))
        return Date().time - timeStamp.time > Constants.NETWORK_FETCH_INTERVAL
    }

    /**
     * Call Api to get project list
     */
    fun updateProjects(apiInterface: ApiInterface) {
        loading.set(true)
        Log.d(LOG_TAG, "Loading Started")
        apiInterface.projects
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Subscriber<List<Project>>() {
                    override fun onCompleted() {

                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                        loading.set(false)
                    }

                    override fun onNext(projects: List<Project>) {
                        Log.d(LOG_TAG, "Inserting in progress")
                        DatabaseJob().execute(projects)
                    }
                })
    }

    /**
     * Insert list of projects into DB.

     * @param projects to insert.
     */
    @WorkerThread
    private fun insertIntoDataBase(projects: List<Project>): Int {
        return projectsDao.insertAll(projects).size
    }


    /**
     * Asynchronous job for database insertion.
     */
    private inner class DatabaseJob : AsyncTask<List<Project>, Int, Int>() {

        override fun doInBackground(vararg params: List<Project>): Int? {
            try {
                //Just for a horror
                Thread.sleep(3000)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }

            return insertIntoDataBase(params[0])
        }

        override fun onPostExecute(length: Int?) {
            super.onPostExecute(length)
            Log.d(LOG_TAG, "Inserted count" + length!!)
            loading.set(false)
            Log.d(LOG_TAG, "Loading ended")
        }
    }

    companion object {
        private val LOG_TAG = "Project Repo"
    }

}
