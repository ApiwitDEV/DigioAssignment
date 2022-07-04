package com.example.digioassignment.viewmodel

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.digioassignment.data.dataModel.DataModel
import com.example.digioassignment.data.dataModel.FoodModelItem
import com.example.digioassignment.repositories.Repositories
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DataViewModel(repositories : Repositories) : ViewModel() {
    var position : Int = 0
    private var _remoteData : MutableLiveData<ArrayList<FoodModelItem>> = MutableLiveData()
    var remoteData : LiveData<ArrayList<FoodModelItem>> = _remoteData

    init {
        if (repositories.getDataFromDatabase().isEmpty()) {
            Toast.makeText(repositories.context,"DATABASE EMPTY",Toast.LENGTH_LONG).show()
            repositories.getDataFromNetwork().enqueue(object : Callback<DataModel> {
                override fun onResponse(
                    call: Call<DataModel>,
                    response: Response<DataModel>
                ) {
                    if (response.isSuccessful) {
                        val items = response.body()!! as ArrayList<FoodModelItem>
                        _remoteData.value = items
                        for (i in items) {
                            val item = FoodModelItem(
                                i.calories,i.carbos,"i.country",i.description,
                                i.difficulty,i.fats,i.headline,i.id,i.image,
                                i.name,i.proteins,i.thumb,i.time
                            )
                            _remoteData.value!!.add(item)
                            repositories.insertDataToDatabase(item)
                        }
                    }
                    else {
                    }
                }
                override fun onFailure(call: Call<DataModel>, t: Throwable) {
                }
            })
        }
        else {
            _remoteData.value = repositories.getDataFromDatabase() as ArrayList<FoodModelItem>
        }
    }
}