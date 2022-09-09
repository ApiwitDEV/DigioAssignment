package com.example.digioassignment.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.digioassignment.R
import com.example.digioassignment.databinding.ActivityMainBinding
import com.example.digioassignment.data.remoteDataSource.RemoteData
import com.example.digioassignment.repositories.Repositories
import com.example.digioassignment.viewmodel.DataViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this

        val remoteData = RemoteData()
        val repositories = Repositories(remoteData,baseContext)
        val dataViewModel = DataViewModel(repositories)
        dataViewModel.repositories.load(binding,dataViewModel)

        binding.topAppBar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.favoriteOnly -> {
                    dataViewModel.chooseDataset(1)
                    binding.adapter!!.notifyDataSetChanged()
                    true
                }
                R.id.all -> {
                    dataViewModel.chooseDataset(0)
                    binding.adapter!!.notifyDataSetChanged()
                    true
                }
                else -> false
            }
        }
    }
}