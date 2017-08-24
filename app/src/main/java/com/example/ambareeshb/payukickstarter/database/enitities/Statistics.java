package com.example.ambareeshb.payukickstarter.database.enitities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by ambareesh on 24/8/17.
 */

@Entity
public class Statistics {
    @PrimaryKey
    @SerializedName("types")
    private EntityTypes types;

    @SerializedName("time_stamp")
    private Date timeStamp;

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public void setTypes(EntityTypes types) {
        this.types = types;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public EntityTypes getTypes() {
        return types;
    }
}
