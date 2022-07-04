package com.example.digioassignment.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.drawToBitmap
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.digioassignment.R
import com.example.digioassignment.databinding.ItemBinding
import com.example.digioassignment.viewmodel.DataViewModel

class FoodListAdapter(private val dataViewModel: DataViewModel) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ItemBinding = DataBindingUtil
            .inflate(inflater, R.layout.item,parent,false)

        return FoodListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder as FoodListViewHolder
        dataViewModel.position = position
        holder.binding.viewModel = dataViewModel
        holder.binding.fevorite.setOnClickListener {
            holder.binding.fevorite.setImageResource(R.drawable.ic_baseline_favorite_24)
        }
        holder.binding.executePendingBindings()
    }

    override fun getItemCount(): Int {
        return dataViewModel.remoteData.value!!.size
    }

    class FoodListViewHolder(val binding: ItemBinding) : RecyclerView.ViewHolder(binding.root) {

    }
}