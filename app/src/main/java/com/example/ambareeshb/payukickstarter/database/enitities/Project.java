package com.example.ambareeshb.payukickstarter.database.enitities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;


import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by ambareeshb on 13/08/17.
 */

@Entity

public class Project {
    //    "s.no":65,
//            "amt.pledged":26817,
//            "blurb":"Our design philosophy embraces simplicity, enabling us to build a safe, high performance, and cost effective Hyperloop pod.",
//            "by":"Waterloop",
//            "country":"CA",
//            "currency":"cad",
//            "end.time":"2016-11-18T19:39:52-05:00",
//            "location":"Waterloo, Canada",
//            "percentage.funded":134,
//            "num.backers":"19349",
//            "state":"ON",
//            "title":"Waterloop: The Canadian SpaceX Hyperloop Competition Team",
//            "type":"Town",
//            "url":"/projects/1629380361/waterloop-the-canadian-spacex-hyperloop-competitio?ref=discovery"
    @PrimaryKey
    @SerializedName("s.no")
    private int slNo;
    @SerializedName("amt.pledged")
    private long amtPledged;
    @SerializedName("blurb")
    private String blurb;
    @SerializedName("title")
    private String title;
    @SerializedName("num.backers")
    private String backers;
    @SerializedName("end.time")
    private Date endTime;
    @SerializedName("timeStamp")
    private Date timeStamp;
    @SerializedName("url")
    private String projectUrl;


    public int getSlNo() {
        return slNo;
    }

    public void setSlNo(int slNo) {
        this.slNo = slNo;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public long getAmtPledged() {
        return amtPledged;
    }

    public void setAmtPledged(long amtPledged) {
        this.amtPledged = amtPledged;
    }

    public String getBackers() {
        return backers;
    }

    public void setBackers(String backers) {
        this.backers = backers;
    }

    public String getBlurb() {
        return blurb;
    }

    public void setBlurb(String blurb) {
        this.blurb = blurb;
    }

    public String getProjectUrl() {
        return projectUrl;
    }

    public void setProjectUrl(String projectUrl) {
        this.projectUrl = projectUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getTimeStamp() {
        if (this.timeStamp == null) timeStamp = new Date();
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }
}
