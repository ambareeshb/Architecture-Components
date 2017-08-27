package com.example.ambareeshb.payukickstarter.viewmodels;

import android.app.Application;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.content.ComponentName;
import android.content.Context;
import android.databinding.ObservableBoolean;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import com.example.ambareeshb.payukickstarter.App;
import com.example.ambareeshb.payukickstarter.Constants;
import com.example.ambareeshb.payukickstarter.database.enitities.Project;
import com.example.ambareeshb.payukickstarter.repositories.ProjectsRepository;
import com.example.ambareeshb.payukickstarter.services.JobService;

import java.util.List;

/**
 * Created by ambareeshb on 13/08/17.
 */

public class ProjectListViewModel extends AndroidViewModel {

    private ProjectsRepository projects;
    private ObservableBoolean loading;


    public ProjectListViewModel(Application application) {
        super(application);
        Log.d("ViewModel","Created");
        projects = ((App) application).getApplicationComponent().projectsRepository();
        loading = projects.getLoading();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //scheduleJob(application,getProjectDataJobInfo(application));
        }
    }

    /**
     * Flag for indicating loading.
     * @return whether loading.
     */
    public ObservableBoolean getLoading() {
        return loading;
    }

    /**
     * Get LiveData list of projects .
     */

    public LiveData<List<Project>> getProjects() {
        return projects.fetchProjects();
    }

    @Override
    protected void onCleared() {
        Log.d("ViewModel","Cleared called");
        super.onCleared();
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private int scheduleJob(Application application, JobInfo info) {
        return ((JobScheduler) application.getSystemService(Context.JOB_SCHEDULER_SERVICE))
                .schedule(info);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private JobInfo getProjectDataJobInfo(Application application) {
        ComponentName serviceComponent = new ComponentName(application.getApplicationContext(),
                JobService.class);
        return new JobInfo.Builder(Constants.JOB_ID, serviceComponent)
                .setBackoffCriteria(Constants.DATA_RETRIEVAL_INTERVAL, JobInfo.BACKOFF_POLICY_LINEAR)
                .setPeriodic(Constants.DATA_RETRIEVAL_INTERVAL)
                .build();
    }
}
