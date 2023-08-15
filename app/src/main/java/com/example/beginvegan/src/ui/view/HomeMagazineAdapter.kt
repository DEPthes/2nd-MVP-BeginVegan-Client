package com.example.beginvegan.src.ui.view

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class HomeMagazineAdapter(mainHomeFragment: MainHomeFragment): FragmentStateAdapter(mainHomeFragment) {
//    private var listener: OnItemClickListener? = null

    val fragmentList = listOf<Fragment>(
        HomeMagazineVeganDefineFragment(),
        HomeMagazineVeganTypesFragment()
    )

    override fun getItemCount(): Int { return fragmentList.size }

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
        getPosition(position)
    }

//    interface OnItemClickListener{
//        fun onItemClick(position: Int)
//    }
//
//    fun setOnItemClickListener(listener: OnItemClickListener){
//        this.listener = listener
//    }

    fun getPosition(index:Int):Int{
        return index
    }
}