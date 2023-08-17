package com.example.beginvegan.src.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView
import com.example.beginvegan.databinding.ItemRecipeBinding
import com.example.beginvegan.src.ui.view.mainhome.HomeRecommendRestAdapter

class RecipeListRVAdapter(private val recipeNames: ArrayList<String>): RecyclerView.Adapter<RecipeListRVAdapter.RecycleViewHolder>() {
    private var listener: OnItemClickListener? = null

//    val recipeNames = listOf(
//        "Sample1", "Sample2", "Sample3", "Sample4", "Sample5"
//    )

    inner class RecycleViewHolder(private val binding: ItemRecipeBinding):
        RecyclerView.ViewHolder(binding.root){
        fun bind(recipeName:String){
            binding.tvRecipeName.setText(recipeName)
        }
    }

    //
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecycleViewHolder {
        val binding = ItemRecipeBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return RecycleViewHolder(binding)
    }

    override fun getItemCount(): Int = recipeNames.size

    override fun onBindViewHolder(holder: RecycleViewHolder, position: Int) {
        val item = recipeNames[position]
        holder.bind(item)

        if(position != RecyclerView.NO_POSITION){
            holder.itemView.setOnClickListener {
                listener?.onItemClick(holder.itemView, recipeNames[position], position)
                Log.d("TAG","Adapter")
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