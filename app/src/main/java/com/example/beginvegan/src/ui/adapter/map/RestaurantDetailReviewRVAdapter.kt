package com.example.beginvegan.src.ui.adapter.map

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.beginvegan.databinding.ItemRestaurantReviewBinding
import com.example.beginvegan.src.data.model.restaurant.ReviewDetail

class RestaurantDetailReviewRVAdapter(private val dataList: ArrayList<ReviewDetail>) :
    RecyclerView.Adapter<RestaurantDetailReviewRVAdapter.DataViewHolder>() {

    inner class DataViewHolder(private val binding: ItemRestaurantReviewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            // bind
            binding.tvReviewContent.text = dataList[position].content
            binding.tvReviewUsername.text = dataList[position].user.name
            binding.tvReviewDate.text = dataList[position].user.createdDate
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DataViewHolder {
        val binding =
            ItemRestaurantReviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DataViewHolder(binding)
    }


    override fun onBindViewHolder(
        holder: DataViewHolder,
        position: Int
    ) {
        holder.bind(position)
    }


    override fun getItemCount(): Int = dataList.size

    override fun getItemViewType(position: Int): Int {
        return position
    }

}