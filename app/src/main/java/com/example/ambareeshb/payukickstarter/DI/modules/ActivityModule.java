package com.example.ambareeshb.payukickstarter.DI.modules;

import android.support.v4.app.FragmentManager;

import com.example.ambareeshb.payukickstarter.ui.MainActivity;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ambareesh on 18/8/17.
 */

@Module
@Singleton
public class ActivityModule {
    private MainActivity mainActivity;

    public ActivityModule(MainActivity activity) {
        this.mainActivity = activity;
    }

    @Provides
    MainActivity provideMainActivity(){
        return this.mainActivity;
    }
    @Provides
    FragmentManager provideFragementManger(MainActivity activity) {
        return activity.getSupportFragmentManager();
    }
}
