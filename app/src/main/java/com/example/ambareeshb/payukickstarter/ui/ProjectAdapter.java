package com.example.ambareeshb.payukickstarter.ui;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.ambareeshb.payukickstarter.R;
import com.example.ambareeshb.payukickstarter.database.Project;
import com.example.ambareeshb.payukickstarter.databinding.LayoutProjectBinding;

import java.util.List;

import butterknife.BindView;

/**
 * Created by ambareeshb on 13/08/17.
 */

public class ProjectAdapter extends RecyclerView.Adapter<ProjectAdapter.ViewHolder> {
    private List<Project> projects;


    public ProjectAdapter(List<Project> projects){
        this.projects = projects;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutProjectBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()),R.layout.layout_project,parent,false);
        return new ViewHolder(binding);

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setBinding(projects.get(position));
    }

    @Override
    public int getItemCount() {
        return (null == projects ? 0 : projects.size());
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
    LayoutProjectBinding layoutProjectBinding;
        @BindView(R.id.tv_project_title)
        AppCompatTextView textTitle;
        @BindView(R.id.tv_project_blurp)
        AppCompatTextView textBlurp;
        @BindView(R.id.tv_project_backers_count)
        AppCompatTextView textBackers;
        @BindView(R.id.tv_project_date)
        AppCompatTextView textDate;

        public ViewHolder(LayoutProjectBinding binding) {
            super(binding.getRoot());

        }

        public void setBinding(Project project) {
            layoutProjectBinding.setProject(project);
            layoutProjectBinding.executePendingBindings();

        }
    }
}
