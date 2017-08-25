package com.example.ambareeshb.payukickstarter.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

import com.example.ambareeshb.payukickstarter.database.daos.ProjectsDao;
import com.example.ambareeshb.payukickstarter.database.enitities.Project;

/**
 * Created by ambareeshb on 13/08/17.
 */

@Database(entities = {Project.class}, version = 3,exportSchema = false)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {
    public abstract ProjectsDao projectsDao();
}
