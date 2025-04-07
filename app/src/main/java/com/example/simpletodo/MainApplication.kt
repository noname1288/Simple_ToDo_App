package com.example.simpletodo

import android.app.Application
import androidx.room.Room
import com.example.simpletodo.database.TodoDatabase

class MainApplication : Application() {

    /*create database at once when app start first*/
    companion object{
        lateinit var instanceDB : TodoDatabase
    }

    override fun onCreate() {
        super.onCreate()
        instanceDB = Room.databaseBuilder(
            applicationContext,
            TodoDatabase::class.java,
            TodoDatabase.NAME
        ).build()
    }
}