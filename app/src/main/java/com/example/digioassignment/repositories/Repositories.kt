package com.example.digioassignment.repositories

import android.content.Context
import android.widget.Toast
import com.example.digioassignment.data.dataModel.DataModel
import com.example.digioassignment.data.dataModel.FoodModelItem
import com.example.digioassignment.data.localDataSource.FoodDatabase
import com.example.digioassignment.data.remoteDataSource.RemoteData
import com.example.digioassignment.databinding.ActivityMainBinding
import com.example.digioassignment.ui.FoodListAdapter
import com.example.digioassignment.viewmodel.DataViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repositories(
    private var remoteData: RemoteData,
    private val context: Context
) {
    private var db : FoodDatabase = FoodDatabase.getInstance(context.applicationContext)

    private fun getDataFromNetwork() : Call<DataModel> {
        return remoteData.retrofitService.getFood()
    }
    fun getDataFromDatabase() : List<FoodModelItem> {
        return db.foodDao().getAll()
    }
    fun insertDataToDatabase(foodItem: FoodModelItem) {
        db.foodDao().inset(foodItem)
    }
    fun updateDataToDatabase(foodItem: FoodModelItem) {
        db.foodDao().update(foodItem)
    }
    fun getFavoriteItem() : List<FoodModelItem> {
        return db.foodDao().selectFavorite()
    }

    fun load(binding: ActivityMainBinding,dataViewModel: DataViewModel) {
        if (getDataFromDatabase().isEmpty()) {
            Toast.makeText(context,"SAVE DATA FROM API TO LOCAL DATASET", Toast.LENGTH_LONG).show()
            getDataFromNetwork().enqueue(object : Callback<DataModel> {
                override fun onResponse(
                    call: Call<DataModel>,
                    response: Response<DataModel>
                ) {
                    if (response.isSuccessful) {
                        val items = response.body()!! as ArrayList<FoodModelItem>
                        dataViewModel.setData(items)
                        dataViewModel.chooseDataset(3)
                        binding.adapter = FoodListAdapter(dataViewModel)
                        for (i in items) {
                            val item = FoodModelItem(
                                i.calories,i.carbos,i.country,i.description,
                                i.difficulty,i.fats,i.headline,i.id,i.image,
                                i.name,i.proteins,i.thumb,i.time,false
                            )
                            insertDataToDatabase(item)
                        }
                    }
                    else {
                        //
                    }
                }
                override fun onFailure(call: Call<DataModel>, t: Throwable) {
                }
            })
        }
        else {
            dataViewModel.setData(getDataFromDatabase() as ArrayList<FoodModelItem>)
            dataViewModel.chooseDataset(0)
            binding.adapter = FoodListAdapter(dataViewModel)
        }
    }
}