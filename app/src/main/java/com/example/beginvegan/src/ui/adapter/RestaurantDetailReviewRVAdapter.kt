package com.example.beginvegan.src.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.beginvegan.databinding.ItemRestaurantReviewBinding
import com.example.beginvegan.databinding.ItemVeganmapRestaurantBinding

class RestaurantDetailReviewRVAdapter (private val dataList: ArrayList<String>): RecyclerView.Adapter<RestaurantDetailReviewRVAdapter.DataViewHolder>(){
    private var listener: OnItemClickListener? = null

    inner class DataViewHolder(private val binding: ItemRestaurantReviewBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(){
            // bind
        }
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DataViewHolder {
        val binding = ItemRestaurantReviewBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return DataViewHolder(binding)
    }


    override fun onBindViewHolder(
        holder: DataViewHolder,
        position: Int
    ) {
        holder.bind()
    }
    override fun getItemCount(): Int = dataList.size

    override fun getItemViewType(position: Int): Int {
        return position
    }
    interface OnItemClickListener {
        fun onItemClick(v: View, data: String, position: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener){
        this.listener = listener
    }
}