package com.example.beginvegan.src.ui.adapter.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.beginvegan.R
import com.example.beginvegan.databinding.ItemHomeRecommendRestaurantBinding
import com.example.beginvegan.src.data.model.restaurant.NearRestaurant

class HomeRecommendRestRVAdapter(
    private val context: Context,
    private val recommendRestList: ArrayList<NearRestaurant>
) :
    RecyclerView.Adapter<HomeRecommendRestRVAdapter.DataViewHolder>() {
    private var listener: OnItemClickListener? = null
    inner class DataViewHolder(private val binding: ItemHomeRecommendRestaurantBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            binding.tvRestaurantName.text = recommendRestList[position].name
            if (!recommendRestList[position].imageUrl.isNullOrEmpty()) {
                Glide.with(context).load(recommendRestList[position].imageUrl).transform(CenterCrop(), RoundedCorners(8))
                    .into(binding.ivRestaurantImg)
            } else {
                Glide.with(context).load(R.drawable.test_res)
                    .transform(CenterCrop(), RoundedCorners(8)).into(binding.ivRestaurantImg)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DataViewHolder {
        val binding = ItemHomeRecommendRestaurantBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return DataViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(position)
        if (position != RecyclerView.NO_POSITION) {
            holder.itemView.setOnClickListener {
                listener?.onItemClick(holder.itemView, recommendRestList[position], position)
            }
        }
    }

    override fun getItemCount(): Int = recommendRestList.size

    override fun getItemViewType(position: Int): Int {
        return position
    }

    interface OnItemClickListener {
        fun onItemClick(v: View, data: NearRestaurant, position: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }
}