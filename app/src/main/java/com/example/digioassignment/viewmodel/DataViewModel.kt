package com.example.digioassignment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.digioassignment.data.dataModel.FoodModelItem
import com.example.digioassignment.repositories.Repositories

class DataViewModel(val repositories: Repositories) : ViewModel() {
    var position : Int = 0
    private var _remoteData : MutableLiveData<ArrayList<FoodModelItem>> = MutableLiveData()
    var remoteData : LiveData<ArrayList<FoodModelItem>> = _remoteData

    fun setData(items: ArrayList<FoodModelItem>) {
        _remoteData.value = items
    }
    fun setFavorite(foodItem: FoodModelItem, favorite: Boolean) {
        foodItem.favorite = favorite
        repositories.updateDataToDatabase(foodItem)
    }
}