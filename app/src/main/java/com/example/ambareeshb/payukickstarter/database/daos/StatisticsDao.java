package com.example.ambareeshb.payukickstarter.database.daos;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;

import com.example.ambareeshb.payukickstarter.database.enitities.Statistics;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Created by ambareesh on 24/8/17.
 */

@Dao
public interface StatisticsDao {

    @Insert(onConflict=REPLACE)
    void updateTimeStamp(Statistics statistics);
}
