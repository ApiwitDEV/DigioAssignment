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

    private var _favoriteList : MutableLiveData<ArrayList<FoodModelItem>> = MutableLiveData()
    var favoriteList : LiveData<ArrayList<FoodModelItem>> = _favoriteList

    private var _filter : MutableLiveData<ArrayList<FoodModelItem>> = MutableLiveData()
    var filter : LiveData<ArrayList<FoodModelItem>> = _filter

    fun setData(items: ArrayList<FoodModelItem>) {
        _remoteData.value = items
    }

    fun setFavorite(foodItem: FoodModelItem, favorite: Boolean) {
        foodItem.favorite = favorite
        repositories.updateDataToDatabase(foodItem)
    }

    fun chooseDataset(select : Int) {
        if (select == 1) {
            _filter.value = repositories.getFavoriteItem() as ArrayList<FoodModelItem>
        }
        else if (select == 0) {
            _filter.value = repositories.getDataFromDatabase() as ArrayList<FoodModelItem>
        }
        else {
            _filter.value = _remoteData.value
        }
    }
}