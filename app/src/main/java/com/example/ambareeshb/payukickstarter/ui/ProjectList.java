package com.example.ambareeshb.payukickstarter.ui;


import android.arch.lifecycle.LifecycleFragment;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ambareeshb.payukickstarter.App;
import com.example.ambareeshb.payukickstarter.R;
import com.example.ambareeshb.payukickstarter.database.Project;
import com.example.ambareeshb.payukickstarter.viewmodels.ProjectListViewModel;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProjectList extends LifecycleFragment {


    public ProjectList() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_project_list, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ProjectListViewModel model = ViewModelProviders.of(this).get(ProjectListViewModel.class);
        model.getProjects().observe(this, new Observer<List<Project>>() {
            @Override
            public void onChanged(@Nullable List<Project> projects) {
                //update ui
                Log.d("Got data","got fata");
                ((App)(getActivity().getApplication())).getmDb().projectsDao().insertAll(projects);
                List<Project> projects1 = ((App)(getActivity().getApplication())).getmDb().projectsDao().findProjectsWithName("Cata");
                Log.d("Got data","got data");
            }
        });
    }
}
