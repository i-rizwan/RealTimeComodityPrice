package com.infigo.realtimecomodityapp.localDB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.infigo.realtimecomodityapp.models.Commodity

@Database(entities = [Commodity::class],version = 2, exportSchema = false)
abstract class LocalDatabase:RoomDatabase() {

    abstract fun commodityDao(): RoomDBDao

    companion object{

        @Volatile
        private var INSTANCE: LocalDatabase?=null

        fun getDataBase(context: Context): LocalDatabase?{

            if (INSTANCE ==null){
                synchronized(LocalDatabase::class.java){
                    if (INSTANCE ==null){
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            LocalDatabase::class.java,
                            "community_db").build()
                    }
                }
            }
            return INSTANCE
        }
    }
}