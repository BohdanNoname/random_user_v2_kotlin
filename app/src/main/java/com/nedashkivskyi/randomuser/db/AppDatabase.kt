package com.nedashkivskyi.randomuser.db

import android.content.Context
import androidx.room.*
import com.nedashkivskyi.randomuser.pojo.Result

@Database(entities = [Result::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun personDao(): PersonDao

    companion object{
        private var instance: AppDatabase? = null
        fun getDb(context: Context): AppDatabase {
            if (instance != null){
                return instance as AppDatabase
            }
            synchronized(this){
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "person_data_base")
                    .build()
            }
            return instance as AppDatabase
        }
    }

}