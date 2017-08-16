package com.example.ambareeshb.payukickstarter.DI.modules;

import com.example.ambareeshb.payukickstarter.Api.ApiInterface;
import com.example.ambareeshb.payukickstarter.helpers.RetrofitHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ambareeshb on 16/08/17.
 */
@Module
public class NetworkModule {
    @Provides
    @Singleton
    ApiInterface provideApiInterface(){
        return RetrofitHelper.initRetrofit(ApiInterface.class);
    }
}
