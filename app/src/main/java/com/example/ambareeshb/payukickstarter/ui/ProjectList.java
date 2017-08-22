package com.example.ambareeshb.payukickstarter.ui;


import android.arch.lifecycle.LifecycleFragment;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ambareeshb.payukickstarter.R;
import com.example.ambareeshb.payukickstarter.database.Project;
import com.example.ambareeshb.payukickstarter.helpers.FragmentUtils;
import com.example.ambareeshb.payukickstarter.viewmodels.ProjectListViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProjectList extends LifecycleFragment implements ProjectAdapter.OnClickListener{
    @BindView(R.id.rv_project_list)
    RecyclerView projectRecycler;

    private ProjectAdapter adapter;
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
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ProjectListViewModel model = ViewModelProviders.of(this).get(ProjectListViewModel.class);
        model.getProjects().observe(this, new Observer<List<Project>>() {
            @Override
            public void onChanged(@Nullable List<Project> projects) {
                //update ui
                if(adapter == null){
                    adapter = new ProjectAdapter(projects,ProjectList.this);
                    projectRecycler.setLayoutManager(new
                            LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
                    projectRecycler.setAdapter(adapter);
                }
                else adapter.setProjects(projects);
            }
        });
    }



    @Override
    public void onClicked(String url) {
    loadWebView(url);
    }
    public void loadWebView(String url){
        Log.d("Loading url",""+"http://www.kickstarter.com/"+url);
        new FragmentUtils(getChildFragmentManager())
                .replace(R.id.fragment_container,ProjectFragment.newInstance("http://www.kickstarter.com"+url))
                .addToBackStack(true,"")
                .commit();
    }
}
