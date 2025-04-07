package com.example.simpletodo.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.simpletodo.database.dao.TodoDao
import com.example.simpletodo.database.entity.Todo

@Database(entities = [Todo::class], version =1)
@TypeConverters(Converters::class)
abstract class TodoDatabase : RoomDatabase() {
    companion object{
        const val NAME = "Todo_Database"
    }

    abstract fun getTodoDao() : TodoDao
}