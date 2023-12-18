package com.example.beginvegan.src.ui.adapter.recipe

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.beginvegan.databinding.ItemRecipeBinding
import com.example.beginvegan.src.data.model.recipe.RecipeList
import com.example.beginvegan.util.VeganTypes

class RecipeListRVAdapter(private val recipeList: List<RecipeList>):
    RecyclerView.Adapter<RecipeListRVAdapter.RecycleViewHolder>() {
    private var listener: OnItemClickListener? = null

    inner class RecycleViewHolder(private val binding: ItemRecipeBinding):
        RecyclerView.ViewHolder(binding.root){
        fun bind(recipe:RecipeList){
            binding.tvVeganType.text = VeganTypes.valueOf(recipe.veganType).veganType
            binding.tvRecipeName.text = recipe.name
            var ingredients = ""
            for(i:Int in 0 until recipe.ingredients.size){
                if(i!=0){ingredients+=", "}
                ingredients += recipe.ingredients[i].name
            }
            binding.tvRecipeIngredients.text = ingredients
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

    override fun getItemCount(): Int = recipeList.size

    override fun onBindViewHolder(holder: RecycleViewHolder, position: Int) {
        val item = recipeList[position]
        holder.bind(item)

        if(position != RecyclerView.NO_POSITION){
            holder.itemView.setOnClickListener {
                listener?.onItemClick(holder.itemView, recipeList[position], position)
                Log.d("Recipe List Adapater", "${recipeList[position]}")
            }
        }
    }

    //인터페이스
    override fun getItemViewType(position: Int): Int {
        return position
    }
    interface OnItemClickListener {
        fun onItemClick(v: View, data: RecipeList, position: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener){
        this.listener = listener
    }
}