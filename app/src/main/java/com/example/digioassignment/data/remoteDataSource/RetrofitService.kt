package com.example.digioassignment.data.remoteDataSource

import com.example.digioassignment.data.dataModel.DataModel
import retrofit2.Call
import retrofit2.http.GET

interface RetrofitService {
    @GET("android-test/recipes.json")
    fun getFood() : Call<DataModel>
}