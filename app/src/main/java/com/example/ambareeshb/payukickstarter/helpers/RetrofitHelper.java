package com.example.ambareeshb.payukickstarter.helpers;


import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ambareeshb on 13/08/17.
 * Utility class for retrofit library configuration.
 */

public class RetrofitHelper {
    private static Retrofit retrofit;


    public  static <T> T initRetrofit(Class<T> apiInterface){
        if(retrofit == null) {

            retrofit = new Retrofit.Builder()
                    .baseUrl("http://starlord.hackerearth.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(getClient())
                    .build();
        }
        return retrofit.create(apiInterface);
    }

    /**
     * Ok http client with logging interceptor.
     * @return
     */
    public static OkHttpClient getClient() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();
        return client;
    }
}
