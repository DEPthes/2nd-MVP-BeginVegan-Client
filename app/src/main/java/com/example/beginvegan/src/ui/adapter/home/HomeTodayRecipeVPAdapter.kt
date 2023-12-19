package com.example.beginvegan.src.ui.adapter.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.beginvegan.databinding.ItemHomeTodayRecipeBinding
import com.example.beginvegan.src.data.model.recipe.Recipe
import com.example.beginvegan.src.data.model.recipe.RecipeThree
import com.example.beginvegan.util.VeganTypes

class HomeTodayRecipeVPAdapter(
    private val dataList: List<RecipeThree>
) : RecyclerView.Adapter<HomeTodayRecipeVPAdapter.DataViewHolder>() {
    private var listener: OnItemClickListener? = null
    inner class DataViewHolder(private val binding: ItemHomeTodayRecipeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: RecipeThree) {
            binding.tvRecipeTitle.text = data.name
            binding.tvRecipeVeganType.text = VeganTypes.valueOf(data.veganType).veganType
            binding.tvRecipeDescription.text = data.description
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DataViewHolder {
        val binding =
            ItemHomeTodayRecipeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DataViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(dataList[position])
        if (position != RecyclerView.NO_POSITION) {
            holder.itemView.setOnClickListener {
                listener?.onItemClick(holder.itemView, dataList[position], position)
            }
        }
    }

    override fun getItemCount(): Int = dataList.size

    override fun getItemViewType(position: Int): Int {
        return position
    }

    interface OnItemClickListener {
        fun onItemClick(v: View, data: RecipeThree, position: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }
}
//
//class HomeTodayRecipeVPAdapter(mainHomeFragment: MainHomeFragment, private val list:List<RecipeThree>): FragmentStateAdapter(mainHomeFragment) {
//
//    val fragmentList = listOf<Fragment>(
//        HomeRecipe0Fragment(list[0]),
//        HomeRecipe1Fragment(list[1]),
//        HomeRecipe2Fragment(list[2])
//    )
//
//    override fun getItemCount(): Int { return fragmentList.size }
//
//    override fun createFragment(position: Int): Fragment {
////        val fragment = fragmentList[position]
////        fragment.getData
//
//        return fragmentList[position]
//    }
//}