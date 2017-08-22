package com.example.ambareeshb.payukickstarter.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.ambareeshb.payukickstarter.App;
import com.example.ambareeshb.payukickstarter.DI.ActivityComponent;
import com.example.ambareeshb.payukickstarter.DI.ApplicationComponent;
import com.example.ambareeshb.payukickstarter.DI.DaggerActivityComponent;
import com.example.ambareeshb.payukickstarter.DI.Qualifiers;
import com.example.ambareeshb.payukickstarter.DI.modules.ActivityModule;
import com.example.ambareeshb.payukickstarter.R;
import com.example.ambareeshb.payukickstarter.helpers.FragmentUtils;

public class MainActivity extends AppCompatActivity {
     ActivityComponent activityComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        buildActivityComponent();
        setContentView(R.layout.activity_main);
        loadProjectListFragment();

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
}
