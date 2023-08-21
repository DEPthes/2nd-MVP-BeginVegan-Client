package com.example.beginvegan.src.ui.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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
        scrapList = arrayListOf()
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
        // 페이징 처리
        binding.rvMyscrap.addOnScrollListener(object: RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val lastVisibleItemPosition =
                    (recyclerView.layoutManager as LinearLayoutManager).findLastCompletelyVisibleItemPosition()
                val itemTotalCount = 10
                if (lastVisibleItemPosition + 1 == itemTotalCount) {
                    // Last page next data load

                }
            }
        })
    }
}