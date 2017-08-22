package com.example.ambareeshb.payukickstarter;

import android.app.Application;

import com.example.ambareeshb.payukickstarter.DI.ApplicationComponent;
import com.example.ambareeshb.payukickstarter.DI.DaggerApplicationComponent;
import com.example.ambareeshb.payukickstarter.DI.modules.ApplicationModule;
import com.squareup.leakcanary.LeakCanary;


/**
 * Created by ambareeshb on 13/08/17.
 */

public class App extends Application {

    private static ApplicationComponent applicationComponent;
    @Override
    public void onCreate() {
        super.onCreate();
        LeakCanary.install(this);
        initApplicationComponent();

    }


    /**
     * Initialise application component.
     */
    private void initApplicationComponent() {
        applicationComponent = DaggerApplicationComponent.builder().
                applicationModule(new ApplicationModule(this))
                .build();
    }


    public static ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
