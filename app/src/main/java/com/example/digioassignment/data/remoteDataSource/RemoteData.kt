package com.example.digioassignment.data.remoteDataSource

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RemoteData {
    var retrofitService : RetrofitService = Retrofit.Builder()
       .baseUrl("https://hf-android-app.s3-eu-west-1.amazonaws.com/")
       .addConverterFactory(GsonConverterFactory.create())
       .build().create(RetrofitService::class.java)
}