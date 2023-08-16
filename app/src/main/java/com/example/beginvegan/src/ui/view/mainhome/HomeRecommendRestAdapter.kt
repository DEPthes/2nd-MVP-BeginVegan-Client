package com.example.beginvegan.src.ui.view.mainhome

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.beginvegan.databinding.ItemHomeRecommendRestaurantBinding

class HomeRecommendRestAdapter():RecyclerView.Adapter<HomeRecommendRestAdapter.RecyclerViewHolder>() {
    val restaurantNames = listOf<String>(
        "Sample1", "Sample2", "Sample3", "Sample4", "Sample5"
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val binding = ItemHomeRecommendRestaurantBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return RecyclerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        val item = restaurantNames[position % restaurantNames.size]
        holder.bind(item)
    }

    override fun getItemCount() = Int.MAX_VALUE

    class RecyclerViewHolder(private val binding: ItemHomeRecommendRestaurantBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(restName: String) {
//            binding.ivRestaurantImg.setImageURI()
            binding.tvRestaurantName.setText(restName)
        }
    }
}