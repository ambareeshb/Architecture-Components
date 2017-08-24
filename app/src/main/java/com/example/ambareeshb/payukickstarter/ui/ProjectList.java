package com.example.ambareeshb.payukickstarter.ui;


import android.arch.lifecycle.LifecycleFragment;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
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
import com.example.ambareeshb.payukickstarter.database.enitities.Project;
import com.example.ambareeshb.payukickstarter.databinding.FragmentProjectListBinding;
import com.example.ambareeshb.payukickstarter.viewmodels.ProjectListViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.ambareeshb.payukickstarter.Constants.WEB_VIEW_PATH;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProjectList extends LifecycleFragment implements ProjectAdapter.OnClickListener {
    FragmentProjectListBinding binding;

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
         binding = DataBindingUtil
                .inflate(inflater,R.layout.fragment_project_list,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ProjectListViewModel model = ViewModelProviders.of(this).get(ProjectListViewModel.class);
        model.getProjects().observe(this, new Observer<List<Project>>() {
            @Override
            public void onChanged(@Nullable List<Project> projects) {
                //update ui
                if (adapter == null) {
                    adapter = new ProjectAdapter(projects, ProjectList.this);
                    projectRecycler.setLayoutManager(new
                            LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
                    projectRecycler.setAdapter(adapter);
                } else adapter.setProjects(projects);
            }
        });
    }


    @Override
    public void onClicked(String url) {
        loadWebView(url);
    }

    /**
     * To start activity that load web view.
     *
     * @param url string.
     */
    public void loadWebView(String url) {
        Log.d("Loading url", "" + "http://www.kickstarter.com/" + url);
        Intent intent = new Intent(this.getActivity(), WebViewActivity.class);
        intent.putExtra(WEB_VIEW_PATH, "http://www.kickstarter.com" + url);
        startActivity(intent);
    }
}
