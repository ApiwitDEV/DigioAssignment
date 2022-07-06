package com.example.digioassignment.data.localDataSource

import androidx.room.*
import com.example.digioassignment.data.dataModel.FoodModelItem

@Dao
interface FoodDao {
    @Query("SELECT * FROM FoodTable")
    fun getAll(): List<FoodModelItem>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun inset(foodItem: FoodModelItem)
    @Update
    fun update(foodItem: FoodModelItem) : Int
    @Query("SELECT * FROM FoodTable WHERE favorite = 1")
    fun selectFavorite() : List<FoodModelItem>
}