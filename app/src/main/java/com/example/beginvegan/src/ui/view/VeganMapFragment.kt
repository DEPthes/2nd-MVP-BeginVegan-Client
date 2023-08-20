package com.example.beginvegan.src.ui.view

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.content.pm.PackageManager
import android.location.LocationManager
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.provider.Settings
import android.util.DisplayMetrics
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.beginvegan.R
import com.example.beginvegan.config.BaseFragment
import com.example.beginvegan.databinding.FragmentVeganMapBinding
import com.example.beginvegan.src.data.model.restaurant.RestaurantFindInterface
import com.example.beginvegan.src.data.model.restaurant.RestaurantFindResponse
import com.example.beginvegan.src.ui.adapter.VeganMapBottomSheetRVAdapter
import com.example.beginvegan.util.Constants.ACCESS_FINE_LOCATION
import net.daum.mf.map.api.MapPOIItem
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapView


class VeganMapFragment : BaseFragment<FragmentVeganMapBinding>(
    FragmentVeganMapBinding::bind,
    R.layout.fragment_vegan_map
), MapView.POIItemEventListener, RestaurantFindInterface {
    private lateinit var dataList: ArrayList<String>
    private lateinit var mapView: MapView
    override fun init() {
        setMapView()
    }
    private fun setMapView(){
        mapView = MapView(this@VeganMapFragment.activity)
        binding.mvVeganMap.addView(mapView)
    }
    private fun getBottomSheetDialogDefaultHeight(): Int {
        return getWindowHeight() * 70 / 100
        // 위 수치는 기기 높이 대비 70%로 높이를 설정
    }

    private fun getWindowHeight(): Int {
        val displayMetrics = DisplayMetrics()
        (context as Activity?)!!.windowManager.defaultDisplay.getMetrics(displayMetrics)
        return displayMetrics.heightPixels
    }
    private fun startTracking() {
        mapView.currentLocationTrackingMode =
            MapView.CurrentLocationTrackingMode.TrackingModeOnWithoutHeading
        mapView.currentLocationTrackingMode = MapView.CurrentLocationTrackingMode.TrackingModeOff
        setRestaurantGps()
    }
    private fun setRestaurantGps() {
        // for문으로 데이터 갯수만큼 돌리기
        var la = 37.223036
        var lo = 127.187954
        for (i: Int in 1..10) {
            val marker = MapPOIItem()
            la -= 0.0001
            lo += 0.0001
            marker.itemName = "Default$i"
            marker.tag = 0
            marker.mapPoint = MapPoint.mapPointWithGeoCoord(la, lo)
            marker.markerType = MapPOIItem.MarkerType.BluePin // 기본으로 제공하는 BluePin 마커 모양.
            marker.selectedMarkerType =
                MapPOIItem.MarkerType.RedPin // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양.
            mapView.addPOIItem(marker)
        }
        mapView.setPOIItemEventListener(this)
    }
    // Set Restaurant List and Click and Test Data
    private fun setRestaurantList() {
//        RestaurantFindService(this).tryPostFindRestaurant()
        dataList = arrayListOf()
        dataList.apply {
            add("hello1")
            add("hello2")
            add("hello3")
            add("hello4")
        }
        val dataRVAdapter = VeganMapBottomSheetRVAdapter(dataList)
        binding.veganmapBottomSheet.rvBottomSheetRestaurantList.adapter = dataRVAdapter
        binding.veganmapBottomSheet.rvBottomSheetRestaurantList.layoutManager =
            LinearLayoutManager(this.context)
        dataRVAdapter.setOnItemClickListener(object :
            VeganMapBottomSheetRVAdapter.OnItemClickListener {
            override fun onItemClick(v: View, data: String, position: Int) {
                Log.d("ItemClick", data)
                showLoadingDialog(requireContext())
                Handler(Looper.myLooper()!!).postDelayed({
                    dismissLoadingDialog()
                    parentFragmentManager.beginTransaction().hide(this@VeganMapFragment)
                        .add(R.id.fl_main, RestaurantDetailFragment()).addToBackStack(null).commit()
                }, 3000)

            }

        })
    }

    override fun onPause() {
        super.onPause()
        binding.mvVeganMap.removeAllViews()
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    override fun onPOIItemSelected(p0: MapView?, p1: MapPOIItem?) {
        Toast.makeText(context, p1!!.itemName.toString(), Toast.LENGTH_SHORT).show()
    }

    override fun onCalloutBalloonOfPOIItemTouched(p0: MapView?, p1: MapPOIItem?) {
    }

    override fun onCalloutBalloonOfPOIItemTouched(
        mapView: MapView?, poiItem: MapPOIItem?, buttonType: MapPOIItem.CalloutBalloonButtonType?
    ) {
        // 말풍선 클릭 시
        val builder = android.app.AlertDialog.Builder(context)
        val itemList = arrayOf("토스트", "마커 삭제", "취소")
        builder.setTitle("${poiItem?.itemName}")
        builder.setItems(itemList) { dialog, which ->
            when (which) {
                0 -> Toast.makeText(context, "토스트", Toast.LENGTH_SHORT).show()  // 토스트
                1 -> mapView?.removePOIItem(poiItem)    // 마커 삭제
                2 -> dialog.dismiss()   // 대화상자 닫기
            }
        }
        builder.show()
    }

    override fun onDraggablePOIItemMoved(p0: MapView?, p1: MapPOIItem?, p2: MapPoint?) {
    }

    override fun onPostFindRestaurantSuccess(response: RestaurantFindResponse) {
    }

    override fun onPostFindRestaurantFailure(message: String) {
    }
}