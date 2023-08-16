package com.example.beginvegan.src.ui.view.mainhome

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.beginvegan.databinding.ItemHomeRecommendRestaurantBinding

class HomeRecommendRestAdapter():RecyclerView.Adapter<HomeRecommendRestAdapter.RecyclerViewHolder>() {
    val restaurantNames = listOf(
        "Sample1", "Sample2", "Sample3", "Sample4", "Sample5"
    )
    private var listener: AdapterView.OnItemClickListener? = null
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
        holder.itemView.setOnClickListener{
//            listener?.onItemClick(holder.itemView,restaurantNames[position],position)
        }
    }

    override fun getItemCount() = Int.MAX_VALUE

//    interface OnItemClickListener {
//        fun onItemClick(v: View, data: String, position: Int)
//    }
//
//    fun setOnItemClickListener(listener: OnItemClickListener){
//        this.listener = listener
//    }


    class RecyclerViewHolder(private val binding: ItemHomeRecommendRestaurantBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(restName: String) {
//            binding.ivRestaurantImg.setImageURI()
            binding.tvRestaurantName.setText(restName)
        }
    }
}