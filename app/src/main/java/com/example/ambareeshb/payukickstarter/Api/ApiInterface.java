package com.example.ambareeshb.payukickstarter.Api;

import com.example.ambareeshb.payukickstarter.database.enitities.Project;

import java.util.List;


import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by ambareeshb on 13/08/17.
 */

public interface ApiInterface {
    @GET("/kickstarter")
    Observable<List<Project>> getProjects();
}
