package com.example.simpletodo.database

import androidx.room.TypeConverter
import java.util.Date

//Date <---> Long
class Converters {
    @TypeConverter
    fun fromDateToLong (date : Date) : Long{
        return date.time
    }

    @TypeConverter
    fun fromLongToDate(time: Long) : Date{
        return Date(time)
    }
}