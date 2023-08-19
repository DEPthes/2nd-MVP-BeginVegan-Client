package com.example.beginvegan.src.ui.adapter

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.beginvegan.R
import com.example.beginvegan.databinding.ItemHomeRecommendRestaurantBinding

class HomeRecommendRestRVAdapter(private val context: Context, private val onItemClick:(position:Int)-> Unit):RecyclerView.Adapter<HomeRecommendRestRVAdapter.RecyclerViewHolder>() {
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
        holder.bind(item, position)
        //item 클릭 시
        holder.itemView.setOnClickListener{
            onItemClick.invoke(position)
        }
    }

    override fun getItemCount() = Int.MAX_VALUE

    class RecyclerViewHolder( private val binding: ItemHomeRecommendRestaurantBinding) :
        RecyclerView.ViewHolder(binding.root) {
        //sample img
        val resImg = listOf(
            R.drawable.test_home_res1,
            R.drawable.test_home_res2,
            R.drawable.test_home_re3,
            R.drawable.test_res2,
            R.drawable.test_res,
        )
        fun bind(restName: String, position: Int) {
//            binding.ivRestaurantImg.setImageURI()
            binding.tvRestaurantName.setText(restName)
            binding.ivRestaurantImg.setImageResource(resImg[position % resImg.size])
        }
    }
}