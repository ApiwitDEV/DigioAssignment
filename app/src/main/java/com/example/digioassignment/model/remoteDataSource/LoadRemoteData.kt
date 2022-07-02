package com.example.digioassignment.model.remoteDataSource

import com.example.digioassignment.model.dataModel.DataModel
import com.example.digioassignment.viewmodel.DataViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LoadRemoteData {
    private val service : Service = Retrofit.Builder()
        .baseUrl("https://hf-android-app.s3-eu-west-1.amazonaws.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build().create(Service::class.java)

    fun load(DataViewModel : DataViewModel) {
        service.getData().enqueue(object : Callback<DataModel> {
            override fun onResponse(
                call: Call<DataModel>,
                response: Response<DataModel>
            ) {
                if (response.isSuccessful) {
                    DataViewModel.fail = false
                    DataViewModel.setRemoteData(response.body()!!)
                }
                else {
                    DataViewModel.statusCode = response.code()
                }
            }
            override fun onFailure(call: Call<DataModel>, t: Throwable) {
                DataViewModel.fail = true
            }
        })
    }
}