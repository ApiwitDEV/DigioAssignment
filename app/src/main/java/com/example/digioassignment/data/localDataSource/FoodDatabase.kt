package com.example.digioassignment.data.localDataSource

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.digioassignment.data.dataModel.FoodModelItem

@Database(entities = [FoodModelItem::class], version = 2, exportSchema = false)
abstract class FoodDatabase : RoomDatabase() {
    abstract fun foodDao(): FoodDao

    companion object {
        @Volatile
        var INSTANCE: FoodDatabase? = null

        fun getInstance(context: Context): FoodDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context,
                        FoodDatabase::class.java,
                        "FoodDatabase"
                    )
                        .fallbackToDestructiveMigration()
                        .allowMainThreadQueries()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}