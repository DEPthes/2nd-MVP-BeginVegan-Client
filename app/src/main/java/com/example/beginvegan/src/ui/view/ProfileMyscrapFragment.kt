package com.example.beginvegan.src.ui.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.beginvegan.R
import com.example.beginvegan.config.BaseFragment
import com.example.beginvegan.databinding.FragmentProfileMyreviewBinding
import com.example.beginvegan.databinding.FragmentProfileMyscrapBinding
import com.example.beginvegan.src.ui.adapter.ProfileMyReviewRVAdapter
import com.example.beginvegan.src.ui.adapter.ProfileMyScrapRVAdapter
import kotlin.math.log

class ProfileMyscrapFragment : BaseFragment<FragmentProfileMyscrapBinding>(
    FragmentProfileMyscrapBinding::bind, R.layout.fragment_profile_myscrap
) {
    private lateinit var scrapList: ArrayList<String>
    override fun init() {
        Log.d("TAG", "init: my scrap")
//        scrapList = arrayListOf()
//        scrapList.apply {
//            add("hello1")
//            add("hello2")
//            add("hello3")
//            add("hello4")
//        }

        initializeViews()
    }

    private fun initializeViews(){
        val scrapAdapter = ProfileMyScrapRVAdapter(scrapList)
        binding.rvMyscrap.adapter = scrapAdapter
        binding.rvMyscrap.layoutManager = LinearLayoutManager(this.context)

        scrapAdapter.setOnItemClickListener(object: ProfileMyScrapRVAdapter.OnItemClickListener{
            override fun onItemClick(v: View, data: String, position: Int) {
                Log.d("TAG", "onItemClick: my scrap")
            }
        })
    }
}