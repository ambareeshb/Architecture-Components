package com.example.ambareeshb.payukickstarter.resources;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.MutableLiveData;

/**
 * Created by ambareesh on 23/8/17.
 */

public abstract class NetworkedResource<Request, Result> {
    private MediatorLiveData<Resource<Result>> resourceLiveData;

    public NetworkedResource() {
        resourceLiveData.setValue(Resource.<Result>loading(null));
        LiveData<Result> result;
        result = loadFromDb();
        if (needNetworkCall(resourceLiveData.getValue())) {
            networkCall(result);
        } else {
            resourceLiveData.setValue(Resource.success(result.getValue(), "SUCCESS"));
        }


    }

    abstract LiveData<Result> loadFromDb();

    abstract boolean needNetworkCall(Resource<Result> result);

    abstract void fetchFromNetwork();

    public void networkCall(LiveData<Result> resource) {
        resourceLiveData.setValue(Resource.loading(resource.getValue()));
        fetchFromNetwork();
    }

    public MutableLiveData<Resource<Result>> getAsLiveData() {
        return resourceLiveData;
    }
}
