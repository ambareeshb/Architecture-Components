package com.example.ambareeshb.payukickstarter.resources;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by ambareesh on 23/8/17.
 */

public class Resource<Data> {
    @Nullable
    private Data data;
    @Nullable
    private String message;
    @NonNull
    private ResourceStatus status;

    public Resource(@NonNull ResourceStatus status, @Nullable Data data, @Nullable String message) {
        this.data = data;
        this.message = message;
        this.status = status;
    }

    public static <Data> Resource<Data> success(@Nullable Data data, @Nullable String message) {
        return new Resource<>(ResourceStatus.SUCCESS, null, message);
    }

    public static <Data> Resource<Data> error(@Nullable Data data,@Nullable String message) {
        return new Resource<>(ResourceStatus.FAILURE, null, message);
    }

    public static <Data> Resource<Data> loading(@Nullable Data data) {
        return new Resource<>(ResourceStatus.LOADING, data, "Loading");
    }
}
