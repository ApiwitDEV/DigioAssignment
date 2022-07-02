package com.example.digioassignment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.digioassignment.model.dataModel.DataModel

class DataViewModel : ViewModel() {
    private var _remoteData : MutableLiveData<DataModel> = MutableLiveData()
    var remoteData : LiveData<DataModel> = _remoteData
    var statusCode : Int = 0
    var fail : Boolean = false

    fun setRemoteData(Data : DataModel) {
        _remoteData.value = Data
    }
}