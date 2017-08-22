package com.example.ambareeshb.payukickstarter.DI;

import android.support.v4.app.FragmentManager;

import com.example.ambareeshb.payukickstarter.DI.modules.ActivityModule;

import dagger.Component;

/**
 * Created by ambareesh on 21/8/17.
 */
@Component(dependencies = ApplicationComponent.class,modules = ActivityModule.class)
@Scopes.ActivityScope
public interface ActivityComponent  {
    FragmentManager fragmentManager();
}
