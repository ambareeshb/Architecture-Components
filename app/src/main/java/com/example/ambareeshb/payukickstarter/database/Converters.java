package com.example.ambareeshb.payukickstarter.database;

import android.arch.persistence.room.TypeConverter;

import com.example.ambareeshb.payukickstarter.database.enitities.EntityTypes;

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

    @TypeConverter
    public static EntityTypes ordinalToEntityType(int ordinal) {
        return (EntityTypes.values()[ordinal]);
    }

    @TypeConverter
    public static int entityTypeToOrdinal(EntityTypes type) {
        return type.ordinal();
    }
}