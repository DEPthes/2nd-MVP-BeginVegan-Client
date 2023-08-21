package com.example.beginvegan.src.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.beginvegan.databinding.ItemProfileMyreviewBinding
import com.example.beginvegan.src.data.model.review.Review
import com.example.beginvegan.src.data.model.review.ReviewDetail

class ProfileMyReviewRVAdapter(private val reviewList: List<ReviewDetail>): RecyclerView.Adapter<ProfileMyReviewRVAdapter.RecycleViewHolder>() {

    inner class RecycleViewHolder(private val binding: ItemProfileMyreviewBinding):
        RecyclerView.ViewHolder(binding.root){
        fun bind(position: Int){
            Log.d("TEST","bind")
            binding.tvRestaurantName.text = reviewList[position].restaurant.name
            binding.tvDate.text =  reviewList[position].date
            binding.tvReviewContent.text =  reviewList[position].content
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecycleViewHolder {
        val binding = ItemProfileMyreviewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return RecycleViewHolder(binding)
    }

    override fun getItemCount(): Int = reviewList.size

    override fun onBindViewHolder(holder: RecycleViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }
}