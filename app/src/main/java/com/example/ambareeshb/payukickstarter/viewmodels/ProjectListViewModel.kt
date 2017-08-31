package com.example.ambareeshb.payukickstarter.viewmodels

import android.app.Application
import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.arch.lifecycle.AndroidViewModel
import android.content.ComponentName
import android.content.Context
import android.databinding.ObservableBoolean
import android.os.Build
import android.support.annotation.RequiresApi
import android.util.Log
import com.example.ambareeshb.payukickstarter.App
import com.example.ambareeshb.payukickstarter.Constants
import com.example.ambareeshb.payukickstarter.repositories.ProjectsRepository
import com.example.ambareeshb.payukickstarter.services.JobService

/**
 * Created by ambareeshb on 13/08/17.
 */

class ProjectListViewModel(application: Application) : AndroidViewModel(application) {

     val projects: ProjectsRepository
    /**
     * Flag for indicating loading.
     * @return whether loading.
     */
    val loading: ObservableBoolean


    init {
        Log.d("ViewModel", "Created")
        projects = App.applicationComponent.projectsRepository()
        loading = projects.loading

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            scheduleJob(application, getProjectDataJobInfo(application))
        }
    }



    override fun onCleared() {
        Log.d("ViewModel", "Cleared called")
        super.onCleared()
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private fun scheduleJob(application: Application, info: JobInfo): Int {
        return (application.getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler)
                .schedule(info)
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private fun getProjectDataJobInfo(application: Application): JobInfo {
        val serviceComponent = ComponentName(application.applicationContext,
                JobService::class.java)
        return JobInfo.Builder(Constants.JOB_ID, serviceComponent)
                .setBackoffCriteria(Constants.DATA_RETRIEVAL_INTERVAL.toLong(), JobInfo.BACKOFF_POLICY_LINEAR)
                .setPeriodic(Constants.DATA_RETRIEVAL_INTERVAL.toLong())
                .build()
    }
}
