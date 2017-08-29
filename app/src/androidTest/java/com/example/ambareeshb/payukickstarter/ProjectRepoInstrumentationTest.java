package com.example.ambareeshb.payukickstarter;

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
    MediatorLiveData<List<Project>> mediatorLiveData;

    public ProjectRepoInstrumentationTest(){
    context =  InstrumentationRegistry.getTargetContext();
    applicationComponentTest = DaggerApplicationComponentTest.builder().
            databaseModule(new DatabaseModule(context))
            .networkModule(new NetworkModule())
            .build();
        mediatorLiveData = new MediatorLiveData();
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
        Log.d("dataaaaaaaa","  "+applicationComponentTest.projectDao().getAllList());
        final LiveData<List<Project>> liveData = applicationComponentTest.projectDao().getAll();
        Log.d("dataaaaaaaa from Live","  "+liveData);
        Assert.assertNotNull(liveData.getValue());

        mediatorLiveData.addSource(liveData, new Observer<List<Project>>() {
            @Override
            public void onChanged(@Nullable List<Project> projects) {
                Log.d("dataaaaaaaa from Live","  "+liveData);

            }
        });
    }

    @Test
    public void shouldFetchTestNull() {
        Assert.assertEquals(applicationComponentTest.projectsRepository()
                .shouldFetch(null), true);

    }
    @Test
    public void shouldFetchWithData(){
        final LiveData<List<Project>> liveData = applicationComponentTest.projectDao().getAll();
        Assert.assertEquals(applicationComponentTest.projectsRepository().shouldFetch(liveData),
                true);
        liveData.observeForever(new Observer<List<Project>>() {
            @Override
            public void onChanged(@Nullable List<Project> projects) {
                Assert.assertEquals(applicationComponentTest.projectsRepository().
                        shouldFetch(liveData), true);
            }
        });
        mediatorLiveData.addSource(liveData, new Observer<List<Project>>() {
            @Override
            public void onChanged(@Nullable List<Project> projects) {
                Assert.assertEquals(applicationComponentTest.projectsRepository().shouldFetch(liveData), true);
            }
        });
    }

    @After
    public void tearDown() throws Exception {
    }

}
