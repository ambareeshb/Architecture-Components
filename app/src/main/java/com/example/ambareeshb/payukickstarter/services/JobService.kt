package com.example.ambareeshb.payukickstarter.services

import android.app.job.JobParameters
import android.os.Build
import android.support.annotation.RequiresApi

import com.example.ambareeshb.payukickstarter.App
import com.example.ambareeshb.payukickstarter.repositories.ProjectsRepository

/**
 * Created by ambareesh on 25/8/17.
 */

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
class JobService : android.app.job.JobService() {
    override fun onStartJob(params: JobParameters): Boolean {
        val repo = App.applicationComponent.projectsRepository()
        try {
            repo.fetchProjects()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return false
    }

    override fun onStopJob(params: JobParameters): Boolean {
        return false
    }
}
