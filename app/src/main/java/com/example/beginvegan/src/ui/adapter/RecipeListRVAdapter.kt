package com.example.beginvegan.src.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.beginvegan.databinding.ItemRecipeBinding
import com.example.beginvegan.src.data.model.recipe.Recipe
import com.example.beginvegan.src.data.model.recipe.RecipeList
import com.example.beginvegan.src.data.model.recipe.RecipeListResponse
import com.example.beginvegan.util.VeganTypes

class RecipeListRVAdapter(private val recipeList: List<RecipeList>):
    RecyclerView.Adapter<RecipeListRVAdapter.RecycleViewHolder>() {
    private var listener: OnItemClickListener? = null
//    private lateinit var filterRecipleList : ArrayList<RecipeList>
//    private var filter:String? =null
    val TAG = "recipe"

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
//        Log.d(TAG, "onBindViewHolder: $filter")
//        if(filter=="전체보기"){
//            holder.bind(item)
//            Log.d(TAG, "filter: 전체보기")
//        }else{
//            if(filter == item.veganType){
//                holder.bind(item)
//                Log.d(TAG, "filter: 필터 o")
//            }else{
//                Log.d(TAG, "filter: 필터 x")
//            }
//        }
        holder.bind(item)

        if(position != RecyclerView.NO_POSITION){
            holder.itemView.setOnClickListener {
                listener?.onItemClick(holder.itemView, recipeList[position], position)
            }
        }
    }

    //필터
//    fun applyFilter(filterIndex:Int){
//        val index = filterIndex-1
//        val enum =enumValues<VeganTypes>()
//        filter = enum[index].name
//        Log.d(TAG, "applyFilter: setFilter $filter")
////        filterRecipleList = arrayListOf()
////        for(i:Int in 0..recipeList.size){
////            if(recipeList[i].veganType == filter){
//////                filterRecipleList.add(recipeList[i])
////            }
////        }
//        notifyDataSetChanged()
//    }

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