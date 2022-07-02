package com.example.digioassignment.model.remoteDataSource

import com.example.digioassignment.model.dataModel.DataModel
import retrofit2.Call
import retrofit2.http.GET

interface Service {
    @GET("android-test/recipes.json")
    fun getData() : Call<DataModel>
}