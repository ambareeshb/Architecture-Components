package com.example.ambareeshb.payukickstarter.database;

import android.arch.persistence.room.TypeConverter;

import java.util.Date;

/**
 * Created by ambareeshb on 13/08/17.
 * Converted class for Room persistent storage.
 */

public class Converters {


    @TypeConverter
    public static Date fromTimestamp(Long value) {
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public static Long dateToTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }


}