package com.example.digioassignment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.digioassignment.data.dataModel.FoodModelItem

class DataViewModel : ViewModel() {
    var position : Int = 0
    private var _remoteData : MutableLiveData<ArrayList<FoodModelItem>> = MutableLiveData()
    var remoteData : LiveData<ArrayList<FoodModelItem>> = _remoteData

    fun setData(items: ArrayList<FoodModelItem>) {
        _remoteData.value = items
    }
}