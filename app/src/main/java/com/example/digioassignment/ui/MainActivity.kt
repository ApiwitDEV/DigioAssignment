package com.example.digioassignment.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.digioassignment.R
import com.example.digioassignment.data.localDataSource.FoodDatabase
import com.example.digioassignment.databinding.ActivityMainBinding
import com.example.digioassignment.data.remoteDataSource.RemoteData
import com.example.digioassignment.data.remoteDataSource.RetrofitService
import com.example.digioassignment.repositories.Repositories
import com.example.digioassignment.viewmodel.DataViewModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var dataViewModel : DataViewModel //by viewModels() is not used

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val retrofitService : RetrofitService = Retrofit.Builder()
            .baseUrl("https://hf-android-app.s3-eu-west-1.amazonaws.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(RetrofitService::class.java)

        val remoteData = RemoteData(retrofitService)
        val repositories = Repositories(remoteData,baseContext)
        dataViewModel = DataViewModel(repositories)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        binding.viewModel = dataViewModel

        binding.adapter = FoodListAdapter(dataViewModel)
    }
}