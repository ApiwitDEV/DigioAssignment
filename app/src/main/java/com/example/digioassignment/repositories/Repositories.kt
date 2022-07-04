package com.example.digioassignment.repositories

import android.content.Context
import com.example.digioassignment.data.dataModel.DataModel
import com.example.digioassignment.data.dataModel.FoodModelItem
import com.example.digioassignment.data.localDataSource.FoodDatabase
import com.example.digioassignment.data.remoteDataSource.RemoteData
import retrofit2.Call

class Repositories(
    private var remoteData: RemoteData,
    val context: Context
) {
    private var db : FoodDatabase = FoodDatabase.getInstance(context.applicationContext)

    fun getDataFromNetwork() : Call<DataModel> {
        return remoteData.retrofitService.getFood()
    }
    fun getDataFromDatabase() : List<FoodModelItem> {
        return db.foodDao().getAll()
    }
    fun insertDataToDatabase(item: FoodModelItem) {
        db.foodDao().inset(item)
    }
}