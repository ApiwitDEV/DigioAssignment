package com.example.digioassignment.ui

import android.view.LayoutInflater
import android.view.ViewGroup
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

        return FoodListViewHolder(binding,dataViewModel)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder as FoodListViewHolder
        dataViewModel.position = position
        holder.binding.viewModel = dataViewModel
        if (dataViewModel.filter.value!![position].favorite) {
            holder.binding.fevorite.setImageResource(R.drawable.ic_baseline_favorite_24)
        }
        else {
            holder.binding.fevorite.setImageResource(R.drawable.ic_baseline_favorite_border_24)
        }
        holder.binding.executePendingBindings()
    }

    override fun getItemCount(): Int {
        return dataViewModel.filter.value!!.size
    }

    class FoodListViewHolder(val binding: ItemBinding,var dataViewModel: DataViewModel)
        : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.fevorite.setOnClickListener {
                val foodItem = dataViewModel.filter.value!![adapterPosition]
                if (!foodItem.favorite) {
                    dataViewModel.setFavorite(foodItem,true)
                    binding.fevorite.setImageResource(R.drawable.ic_baseline_favorite_24)
                }
                else {
                    dataViewModel.setFavorite(foodItem,false)
                    binding.fevorite.setImageResource(R.drawable.ic_baseline_favorite_border_24)
                }
            }
        }
    }
}