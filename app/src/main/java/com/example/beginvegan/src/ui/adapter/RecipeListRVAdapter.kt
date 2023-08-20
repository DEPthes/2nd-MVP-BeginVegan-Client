package com.example.beginvegan.src.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.beginvegan.databinding.ItemRecipeBinding
import com.example.beginvegan.src.data.model.recipe.Recipe
import com.example.beginvegan.src.data.model.recipe.RecipeListResponse

class RecipeListRVAdapter(private val recipeList: List<RecipeListResponse>):
    RecyclerView.Adapter<RecipeListRVAdapter.RecycleViewHolder>() {
    private var listener: OnItemClickListener? = null
    private lateinit var filterRecipleList : ArrayList<Recipe>

    inner class RecycleViewHolder(private val binding: ItemRecipeBinding):
        RecyclerView.ViewHolder(binding.root){
        fun bind(recipe:Recipe){
            binding.tvRecipeName.text = recipe.name
        }
    }

    //
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecycleViewHolder {
        val binding = ItemRecipeBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
//        filterRecipleList = recipeList
        return RecycleViewHolder(binding)
    }

    override fun getItemCount(): Int = filterRecipleList.size

    override fun onBindViewHolder(holder: RecycleViewHolder, position: Int) {
        val item = filterRecipleList[position]
//        if(filter=="전체보기"){
//            holder.bind(item)
//        }else{
//            if(filter == item.veganType){
//                holder.bind(item)
//            }
//        }
        holder.bind(item)

        if(position != RecyclerView.NO_POSITION){
            holder.itemView.setOnClickListener {
                listener?.onItemClick(holder.itemView, filterRecipleList[position], position)
            }
        }
    }

    //필터
    fun applyFilter(filter:String){

    }

    //인터페이스
    override fun getItemViewType(position: Int): Int {
        return position
    }
    interface OnItemClickListener {
        fun onItemClick(v: View, data: Recipe, position: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener){
        this.listener = listener
    }
}