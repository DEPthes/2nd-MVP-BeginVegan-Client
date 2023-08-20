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
import com.example.beginvegan.config.ApplicationClass
import com.example.beginvegan.config.BaseFragment
import com.example.beginvegan.databinding.FragmentVeganMapBinding
import com.example.beginvegan.src.data.model.restaurant.Coordinate
import com.example.beginvegan.src.data.model.restaurant.RestaurantFindInterface
import com.example.beginvegan.src.data.model.restaurant.RestaurantFindResponse
import com.example.beginvegan.src.data.model.restaurant.RestaurantFindService
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
//        showLoadingDialog(requireContext())
        setMapView()
        getBottomSheetDialogDefaultHeight()
//        RestaurantFindService(this).tryPostFindRestaurant(Coordinate(ApplicationClass.xLatitude,ApplicationClass.xLongitude))
    }
    private fun setMapView(){
        mapView = MapView(this@VeganMapFragment.activity)
        binding.mvVeganMap.addView(mapView)
        mapView.setMapCenterPointAndZoomLevel(MapPoint.mapPointWithGeoCoord(ApplicationClass.xLatitude.toDouble(), ApplicationClass.xLongitude.toDouble()), 1, true);
        Log.d("setMapCenterPointAndZoomLevel",ApplicationClass.xLatitude)
        Log.d("setMapCenterPointAndZoomLevel",ApplicationClass.xLongitude)
//        mapView.currentLocationTrackingMode =
//            MapView.CurrentLocationTrackingMode.TrackingModeOnWithMarkerHeadingWithoutMapMoving
//        setRestaurantGps()
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

    private fun setRestaurantGps() {
        // for문으로 데이터 갯수만큼 돌리기
        var la = ApplicationClass.xLatitude.toDouble()
        var lo = ApplicationClass.xLongitude.toDouble()
        for (i: Int in 1..10) {
            val marker = MapPOIItem()
            la -= 0.0001
            lo += 0.0001
            marker.apply{
                itemName = "Default$i"
                tag = 0
                mapPoint = MapPoint.mapPointWithGeoCoord(la, lo)
                markerType = MapPOIItem.MarkerType.CustomImage
                customImageResourceId = R.drawable.marker_spot
            }.isShowCalloutBalloonOnTouch = false
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
        // 마커 처리하기
        Toast.makeText(context, p1!!.itemName.toString(), Toast.LENGTH_SHORT).show()
    }

    override fun onCalloutBalloonOfPOIItemTouched(p0: MapView?, p1: MapPOIItem?) {}

    override fun onCalloutBalloonOfPOIItemTouched(
        mapView: MapView?, poiItem: MapPOIItem?, buttonType: MapPOIItem.CalloutBalloonButtonType?
    ) {}

    override fun onDraggablePOIItemMoved(p0: MapView?, p1: MapPOIItem?, p2: MapPoint?) {}

    override fun onPostFindRestaurantSuccess(response: RestaurantFindResponse) {
        dismissLoadingDialog()
        Log.d("onPostFindRestaurantSuccess",response.toString())
       //
    }

    override fun onPostFindRestaurantFailure(message: String) {
        Log.d("onPostFindRestaurantFailure",message)
    }

    // 트래킹 모드
    private fun startTracking() {
        mapView.currentLocationTrackingMode =
            MapView.CurrentLocationTrackingMode.TrackingModeOnWithoutHeading
        mapView.currentLocationTrackingMode = MapView.CurrentLocationTrackingMode.TrackingModeOff
        setRestaurantGps()
    }
}