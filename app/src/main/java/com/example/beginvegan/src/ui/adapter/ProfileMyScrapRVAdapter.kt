package com.example.beginvegan.src.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.beginvegan.R
import com.example.beginvegan.databinding.ItemProfileMyreviewBinding
import com.example.beginvegan.databinding.ItemProfileMyscrapBinding

class ProfileMyScrapRVAdapter(private val scrapList: ArrayList<String>): RecyclerView.Adapter<ProfileMyScrapRVAdapter.RecycleViewHolder>() {
    private var listener: OnItemClickListener? = null

    //sample img
    val resImg = listOf(
        R.drawable.test_home_res1,
        R.drawable.test_home_res2,
        R.drawable.test_home_re3,
        R.drawable.test_res2,
        R.drawable.test_res,
    )

    inner class RecycleViewHolder(private val binding: ItemProfileMyscrapBinding):
        RecyclerView.ViewHolder(binding.root){
        fun bind(restaurantName:String, time:String, address:String, position: Int){
            Log.d("TEST","bind")
            binding.ivRestaurantImg.setImageResource(resImg[position % resImg.size ])
            binding.tvRestaurantName.text = restaurantName
            binding.tvRestaurantTime.text = time
            binding.tvRestaurantAddress.text = address
            binding.ibDeleteScrap.setOnClickListener {
                //서버 - 북마크

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecycleViewHolder {
        val binding = ItemProfileMyscrapBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return RecycleViewHolder(binding)
    }

    override fun getItemCount(): Int = scrapList.size

    override fun onBindViewHolder(holder: RecycleViewHolder, position: Int) {
        val item = scrapList[position]

        if(position != RecyclerView.NO_POSITION){
            holder.itemView.setOnClickListener {
                listener?.onItemClick(holder.itemView, scrapList[position], position)
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
        this.listener = listener
    }
}