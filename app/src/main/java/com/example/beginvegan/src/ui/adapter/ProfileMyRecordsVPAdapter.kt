package com.example.beginvegan.src.ui.adapter

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.beginvegan.src.ui.view.MainProfileFragment
import com.example.beginvegan.src.ui.view.ProfileMyreviewFragment
import com.example.beginvegan.src.ui.view.ProfileMyscrapFragment

class ProfileMyRecordsVPAdapter(mainProfileFragment: MainProfileFragment): FragmentStateAdapter(mainProfileFragment) {

    val fragmentList = listOf<Fragment>(
        ProfileMyreviewFragment(),
        ProfileMyscrapFragment()
    )

    override fun getItemCount(): Int { return fragmentList.size }

    override fun createFragment(position: Int): Fragment {
        Log.d("TAG", "createFragment: adapter")
        return when (position) {
            0 -> ProfileMyreviewFragment()
            else -> {
                ProfileMyscrapFragment()
            }
        }
    }
}