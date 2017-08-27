package com.example.ambareeshb.payukickstarter.DI.modules;

import android.app.Application;

import com.example.ambareeshb.payukickstarter.App;
import com.example.ambareeshb.payukickstarter.helpers.FragmentUtils;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ambareeshb on 16/08/17.
 */
@Module
public class ApplicationModule {
    private Application app;

    public ApplicationModule(Application app){
        this.app = app;
    }
    @Provides
    @Singleton
    public Application provideApp(){
        return app;
    }
}
