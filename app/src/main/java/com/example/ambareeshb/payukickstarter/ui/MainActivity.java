package com.example.ambareeshb.payukickstarter.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.ambareeshb.payukickstarter.R;
import com.example.ambareeshb.payukickstarter.helpers.FragmentUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadProjectListFragment();
    }

    private void loadProjectListFragment() {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container,new ProjectList())
                .commit();
//        new FragmentUtils(getSupportFragmentManager())
//                .add(R.id.fragment_container,new ProjectList())
//                .setTransition(R.anim.enter_from_right,R.anim.exit_to_right)
//                .commit();
    }
}
