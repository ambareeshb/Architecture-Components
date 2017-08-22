package com.example.ambareeshb.payukickstarter.DI;

import android.support.v4.app.FragmentManager;

import com.example.ambareeshb.payukickstarter.DI.modules.Fragmentmodule;

import dagger.Component;

/**
 * Created by ambareesh on 22/8/17.
 */
@Component(dependencies = ActivityComponent.class,modules = Fragmentmodule.class)
@Scopes.FragmentScope
public interface FragmentComponent {
    FragmentManager fragementManger();
}
