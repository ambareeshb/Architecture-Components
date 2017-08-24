package com.example.ambareeshb.payukickstarter.ui;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.ambareeshb.payukickstarter.R;
import com.example.ambareeshb.payukickstarter.database.enitities.Project;
import com.example.ambareeshb.payukickstarter.databinding.LayoutProjectBinding;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

/**
 * Created by ambareeshb on 13/08/17.
 */

public class ProjectAdapter extends RecyclerView.Adapter<ProjectAdapter.ViewHolder> {
    private List<Project> projects;
    private OnClickListener onClickListner;


    public ProjectAdapter(List<Project> projects,OnClickListener onClickListner){
        this.projects = projects;
        this.onClickListner = onClickListner;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutProjectBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.layout_project,parent,false);
        binding.setOnClicker(onClickListner);
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

    /**
     * View holder class.
     */
    public static class ViewHolder extends RecyclerView.ViewHolder{
        private LayoutProjectBinding layoutProjectBinding;
        private SimpleDateFormat dateFormater;

        public ViewHolder(LayoutProjectBinding binding) {
            super(binding.getRoot());
            layoutProjectBinding = binding;
            dateFormater = new SimpleDateFormat("EEE, dd, MM yyyy", Locale.US);
            layoutProjectBinding.setViewHolder(this);
        }

        public void setBinding(Project project) {
            layoutProjectBinding.setProject(project);
            layoutProjectBinding.executePendingBindings();
        }

        public SimpleDateFormat getDateFormater() {
            return dateFormater;
        }
    }



    public  interface OnClickListener {
         void onClicked(String url);
    }
}
