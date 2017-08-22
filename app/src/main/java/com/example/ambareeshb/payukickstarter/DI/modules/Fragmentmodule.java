package com.example.ambareeshb.payukickstarter.DI.modules;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.example.ambareeshb.payukickstarter.DI.Qualifiers;
import com.example.ambareeshb.payukickstarter.DI.Scopes;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ambareesh on 22/8/17.
 */

@Module
@Scopes.FragmentScope
public class Fragmentmodule {
    Fragment fragment;
    public Fragmentmodule(Fragment fm){
        fragment = fm;
    }
    @Provides@Qualifiers.Named("Fragment")
    public FragmentManager provideFragmentManger(){
        return fragment.getChildFragmentManager();
    }
}
