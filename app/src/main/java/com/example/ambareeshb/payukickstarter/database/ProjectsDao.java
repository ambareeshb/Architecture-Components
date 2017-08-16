package com.example.ambareeshb.payukickstarter.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by ambareeshb on 13/08/17.
 */
@Dao
public interface ProjectsDao {
    @Query("SELECT * FROM Project")
    LiveData<List<Project>> getAll();
    @Query("SELECT * FROM Project where slNo BETWEEN :startSlNo AND :endSlNo")
    List<Project> getProjectsAfter(long startSlNo, long endSlNo);
    @Insert
    void insertAll(List<Project> projects);
    @Query("SELECT * FROM Project WHERE title LIKE :search")
    public List<Project> findProjectsWithName(String search);
}
