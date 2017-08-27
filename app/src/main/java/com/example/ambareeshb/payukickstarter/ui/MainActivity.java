package com.example.ambareeshb.payukickstarter.ui;

import android.Manifest;
import android.app.Application;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;

import com.example.ambareeshb.payukickstarter.App;
import com.example.ambareeshb.payukickstarter.Constants;
import com.example.ambareeshb.payukickstarter.DI.ActivityComponent;
import com.example.ambareeshb.payukickstarter.DI.ApplicationComponent;
import com.example.ambareeshb.payukickstarter.DI.DaggerActivityComponent;
import com.example.ambareeshb.payukickstarter.DI.Qualifiers;
import com.example.ambareeshb.payukickstarter.DI.modules.ActivityModule;
import com.example.ambareeshb.payukickstarter.R;
import com.example.ambareeshb.payukickstarter.helpers.FragmentUtils;
import com.example.ambareeshb.payukickstarter.services.JobService;

import java.util.List;

import pub.devrel.easypermissions.EasyPermissions;

public class MainActivity extends AppCompatActivity implements EasyPermissions.PermissionCallbacks{
     ActivityComponent activityComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        buildActivityComponent();
        setContentView(R.layout.activity_main);
        loadProjectListFragment();
        //scheduleJob(getApplication(),getProjectDataJobInfo(getApplication()));
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        // Forward results to EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }
    private void buildActivityComponent() {
         activityComponent =  DaggerActivityComponent.builder()
                .applicationComponent(App.getApplicationComponent())
                .activityModule(new ActivityModule(this))
                .build();
    }

    private void loadProjectListFragment() {
        new FragmentUtils(getSupportFragmentManager())
                .add(R.id.fragment_container,new ProjectList())
                .setTransition(R.anim.enter_from_right,R.anim.exit_to_right)
                .commit();
    }

    public ActivityComponent getActivityComponent() {
        return activityComponent;

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

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {

    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {

    }
}
