package com.example.beginvegan.src.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.beginvegan.databinding.ItemHomeRecommendRestaurantBinding

class HomeRecommendRestRVAdapter(private val onItemClick:(position:Int)-> Unit):RecyclerView.Adapter<HomeRecommendRestRVAdapter.RecyclerViewHolder>() {
    val restaurantNames = listOf(
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

        //item 클릭 시
        holder.itemView.setOnClickListener{
            onItemClick.invoke(position)
        }
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