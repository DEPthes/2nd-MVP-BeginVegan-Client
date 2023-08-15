package com.example.beginvegan.src.ui.view.mainhome

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.beginvegan.databinding.ItemHomeRecommendRestaurantBinding

class HomeRecommendRestAdapter():RecyclerView.Adapter<HomeRecommendRestAdapter.RecyclerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val binding = ItemHomeRecommendRestaurantBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return RecyclerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    class RecyclerViewHolder(private val binding: ItemHomeRecommendRestaurantBinding):
            RecyclerView.ViewHolder(binding.root){

            }
}