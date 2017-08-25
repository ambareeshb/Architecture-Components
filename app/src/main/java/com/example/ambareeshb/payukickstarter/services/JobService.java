package com.example.ambareeshb.payukickstarter.services;

import android.app.job.JobParameters;
import android.os.Build;
import android.support.annotation.RequiresApi;

import com.example.ambareeshb.payukickstarter.App;
import com.example.ambareeshb.payukickstarter.repositories.ProjectsRepository;

/**
 * Created by ambareesh on 25/8/17.
 */

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class JobService extends android.app.job.JobService {
    @Override
    public boolean onStartJob(JobParameters params) {
        ProjectsRepository repo =
                App.getApplicationComponent().projectsRepository();
        repo.fetchProjects();
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        return false;
    }
}
