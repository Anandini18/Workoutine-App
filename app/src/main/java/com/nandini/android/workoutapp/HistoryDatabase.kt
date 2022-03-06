package com.nandini.android.workoutapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [HistoryEntity::class], version = 1)
public abstract class HistoryDatabase : RoomDatabase() {

    abstract fun historyDao():HistoryDao

    companion object {

    @Volatile
    private var INSTANCE :HistoryDatabase?=null

    fun getInstance(context: Context):HistoryDatabase=INSTANCE?: synchronized(this){
        INSTANCE ?: buildDatabase(context).also {INSTANCE = it}
    }

    private fun buildDatabase(context: Context) =
        Room.databaseBuilder(
            context.applicationContext,
            HistoryDatabase::class.java, "user_database"
        ).allowMainThreadQueries().build()

    }}


//    fun getInstance(context: Context):HistoryDatabase{
//        synchronized(this){
//            var instance=INSTANCE
//
//            if(instance==null){
//                instance= Room.databaseBuilder(
//                    context.applicationContext,
//                    HistoryDatabase::class.java,
//                    "history_database"
//                ).fallbackToDestructiveMigration().build()
//                INSTANCE = instance
//            }
//            return instance
//        }
//
//    }
