package com.example.digioassignment.data.localDataSource

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.digioassignment.data.dataModel.FoodModelItem

@Dao
interface FoodDao {
    @Query("SELECT * FROM FoodTable")
    fun getAll(): List<FoodModelItem>
    @Insert
    fun inset(foodItem: FoodModelItem)
}