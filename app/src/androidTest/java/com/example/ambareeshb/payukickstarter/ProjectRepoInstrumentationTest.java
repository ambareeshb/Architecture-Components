package com.example.ambareeshb.payukickstarter;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.Observer;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.example.ambareeshb.payukickstarter.DI.modules.NetworkModule;
import com.example.ambareeshb.payukickstarter.DaggerTest.ApplicationComponentTest;
import com.example.ambareeshb.payukickstarter.DaggerTest.DaggerApplicationComponentTest;
import com.example.ambareeshb.payukickstarter.DaggerTest.DatabaseModule;
import com.example.ambareeshb.payukickstarter.database.enitities.Project;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by ambareeshb on 27/08/17.
 */

@RunWith(AndroidJUnit4.class)
public class ProjectRepoInstrumentationTest {
    private Context context;
    private ApplicationComponentTest applicationComponentTest;

    public ProjectRepoInstrumentationTest(){
    context =  InstrumentationRegistry.getTargetContext();
    applicationComponentTest = DaggerApplicationComponentTest.builder().
            databaseModule(new DatabaseModule(context))
            .networkModule(new NetworkModule())
            .build();
    initDB();
    }

    @Before
    public void setUp() throws Exception {


    }

    private void initDB() {
        try {
            applicationComponentTest.projectsRepository().updateProjects(applicationComponentTest.apiInterface());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    @Test
    public void testProjectsDB() {
    Assert.assertNotNull(applicationComponentTest.projectDao().getAllList());

    }

    @Test
    public void shouldFetchTestNull() {
        Assert.assertEquals(applicationComponentTest.projectsRepository()
                .shouldFetch(null), true);

    }

    @Test
    public void shouldFetchWithData(){
//        Assert.assertEquals(applicationComponentTest.projectsRepository()
//                .shouldFetch(applicationComponentTest.projectDao().getAllList()),true);
        applicationComponentTest.projectDao().getAll().observeForever(new Observer<List<Project>>() {
            @Override
            public void onChanged(@Nullable List<Project> projects) {
                Assert.assertNotNull(projects);
                Log.d("value changed","changed"+ projects);

            }
        });
    }

    @After
    public void tearDown() throws Exception {
    }

}
