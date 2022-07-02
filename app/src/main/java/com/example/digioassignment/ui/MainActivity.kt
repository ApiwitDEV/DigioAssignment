package com.example.digioassignment.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.example.digioassignment.R
import com.example.digioassignment.databinding.ActivityMainBinding
import com.example.digioassignment.model.remoteDataSource.LoadRemoteData
import com.example.digioassignment.viewmodel.DataViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private val dataViewModel : DataViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        binding.viewModel = dataViewModel

        val loader = LoadRemoteData()
        loader.load(dataViewModel)
    }
}