package com.example.digioassignment.data.dataModel

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "FoodTable")
data class FoodModelItem(
    @ColumnInfo val calories: String,
    @ColumnInfo val carbos: String,
    @ColumnInfo val country: String?,
    @ColumnInfo val description: String,
    @ColumnInfo val difficulty: Int,
    @ColumnInfo val fats: String,
    @ColumnInfo val headline: String,
    @PrimaryKey val id: String,
    @ColumnInfo val image: String,
    @ColumnInfo val name: String,
    @ColumnInfo val proteins: String,
    @ColumnInfo val thumb: String,
    @ColumnInfo val time: String,
    @ColumnInfo var favorite: Boolean
)