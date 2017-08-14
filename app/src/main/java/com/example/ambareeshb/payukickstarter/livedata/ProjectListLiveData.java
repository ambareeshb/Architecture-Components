package com.example.ambareeshb.payukickstarter.livedata;

import android.arch.lifecycle.LiveData;

import com.example.ambareeshb.payukickstarter.Api.ApiInterface;
import com.example.ambareeshb.payukickstarter.database.Project;
import com.example.ambareeshb.payukickstarter.helpers.RetrofitHelper;

import java.util.List;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ambareeshb on 13/08/17.
 */

public class ProjectListLiveData extends LiveData<List<Project>> {
    List<Project> projects;

    @Override
    protected void onActive() {
        super.onActive();
        updateProjects();
    }

    @Override
    protected void onInactive() {
        super.onInactive();
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
                    postValue(projects);
                    }
                });
    }



}
