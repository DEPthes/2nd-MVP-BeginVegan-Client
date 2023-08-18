package com.example.beginvegan.src.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.beginvegan.databinding.ItemProfileMyreviewBinding

class ProfileMyReviewRVAdapter(private val reviewList: ArrayList<String>): RecyclerView.Adapter<ProfileMyReviewRVAdapter.RecycleViewHolder>() {
    private var listener: OnItemClickListener? = null

    inner class RecycleViewHolder(private val binding: ItemProfileMyreviewBinding):
        RecyclerView.ViewHolder(binding.root){
        fun bind(restaurantName:String, date:String, content:String){
            Log.d("TEST","bind")
            binding.tvRestaurantName.text = restaurantName
            binding.tvDate.text = date
            binding.tvReviewContent.text = content
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
        val item = reviewList[position]
        holder.bind("식당 이름","date","content")

        Log.d("TEST","onBindViewHolder")
        if(position != RecyclerView.NO_POSITION){
            Log.d("TAG","Click")
            holder.itemView.setOnClickListener {
                listener?.onItemClick(holder.itemView, reviewList[position], position)
                Log.d("TAG","ClickListener")
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }
    interface OnItemClickListener {
        fun onItemClick(v: View, data: String, position: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener){

        Log.d("TAG","setOnItemClickListener")
        this.listener = listener
    }
}