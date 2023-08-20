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
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.view.View.SCREEN_STATE_OFF
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.beginvegan.R
import com.example.beginvegan.config.ApplicationClass
import com.example.beginvegan.config.BaseFragment
import com.example.beginvegan.databinding.FragmentVeganMapBinding
import com.example.beginvegan.src.data.model.restaurant.Coordinate
import com.example.beginvegan.src.data.model.restaurant.NearRestaurant
import com.example.beginvegan.src.data.model.restaurant.RestaurantFindInterface
import com.example.beginvegan.src.data.model.restaurant.RestaurantFindResponse
import com.example.beginvegan.src.data.model.restaurant.RestaurantFindService
import com.example.beginvegan.src.ui.adapter.VeganMapBottomSheetRVAdapter
import com.example.beginvegan.util.Constants.ACCESS_FINE_LOCATION
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_COLLAPSED
import com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_HALF_EXPANDED
import com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_HIDDEN
import com.google.android.material.bottomsheet.BottomSheetBehavior.State
import net.daum.mf.map.api.MapPOIItem
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapView


class VeganMapFragment : BaseFragment<FragmentVeganMapBinding>(
    FragmentVeganMapBinding::bind,
    R.layout.fragment_vegan_map
), MapView.POIItemEventListener, RestaurantFindInterface{
    private lateinit var dataList: ArrayList<NearRestaurant>
    private lateinit var mapView: MapView
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>
    override fun init() {
//        showLoadingDialog(requireContext())
        setMapView()
        binding.veganmapBottomSheet.clBottomSheet.maxHeight = getBottomSheetDialogDefaultHeight()
        RestaurantFindService(this).tryPostFindRestaurant(Coordinate(ApplicationClass.xLatitude,ApplicationClass.xLongitude))
    }
    private fun setMapView(){
        mapView = MapView(this@VeganMapFragment.activity)
        binding.mvVeganMap.addView(mapView)
        bottomSheetBehavior = BottomSheetBehavior.from(binding.veganmapBottomSheet.clBottomSheet)
        mapView.setMapCenterPointAndZoomLevel(MapPoint.mapPointWithGeoCoord(ApplicationClass.xLatitude.toDouble(), ApplicationClass.xLongitude.toDouble()), 1, true)
        Log.d("setMapCenterPointAndZoomLevel",ApplicationClass.xLatitude)
        Log.d("setMapCenterPointAndZoomLevel",ApplicationClass.xLongitude)
        mapView.currentLocationTrackingMode =
            MapView.CurrentLocationTrackingMode.TrackingModeOnWithHeadingWithoutMapMoving
//        setRestaurantGps()

        mapView.setOnTouchListener { v, event ->
            Log.d("Touch","mapView")
            if(bottomSheetBehavior.state != STATE_COLLAPSED){
                bottomSheetBehavior.state = STATE_COLLAPSED
            }
            false
        }

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

    private fun setRestaurantGps(response: RestaurantFindResponse) {
        // for문으로 데이터 갯수만큼 돌리기
        for(i: Int in 0 until response.information.size){
            val marker = MapPOIItem()
            marker.apply{
                itemName = response.information[i].name
                mapPoint = MapPoint.mapPointWithGeoCoord(ApplicationClass.xLatitude.toDouble()-0.001*i.toDouble(),ApplicationClass.xLongitude.toDouble()+0.001*i.toDouble())
                markerType = MapPOIItem.MarkerType.CustomImage
                customImageResourceId = R.drawable.marker_spot
            }.isShowCalloutBalloonOnTouch = false
            mapView.addPOIItem(marker)
        }
        mapView.setPOIItemEventListener(this)
    }
    // Set Restaurant List and Click and Test Data
    private fun setRestaurantList(response: RestaurantFindResponse) {
        dataList = arrayListOf()
        for(i: Int in 0 until response.information.size){
            dataList.add(response.information[i])
        }
        val dataRVAdapter = VeganMapBottomSheetRVAdapter(requireContext(),dataList)
        binding.veganmapBottomSheet.rvBottomSheetRestaurantList.adapter = dataRVAdapter
        binding.veganmapBottomSheet.rvBottomSheetRestaurantList.layoutManager =
            LinearLayoutManager(this.context)
        dataRVAdapter.setOnItemClickListener(object :
            VeganMapBottomSheetRVAdapter.OnItemClickListener {
            override fun onItemClick(v: View, data: NearRestaurant, position: Int) {
                Log.d("ItemClick", data.toString())
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


    override fun onPOIItemSelected(p0: MapView?, p1: MapPOIItem?) {
        // 마커 처리하기
        bottomSheetBehavior.state = STATE_HALF_EXPANDED
    }

    override fun onCalloutBalloonOfPOIItemTouched(p0: MapView?, p1: MapPOIItem?) {}

    override fun onCalloutBalloonOfPOIItemTouched(
        mapView: MapView?, poiItem: MapPOIItem?, buttonType: MapPOIItem.CalloutBalloonButtonType?
    ) {}

    override fun onDraggablePOIItemMoved(p0: MapView?, p1: MapPOIItem?, p2: MapPoint?) {}

    override fun onPostFindRestaurantSuccess(response: RestaurantFindResponse) {
        Log.d("onPostFindRestaurantSuccess",response.toString())
        setRestaurantGps(response)
        setRestaurantList(response)
    }

    override fun onPostFindRestaurantFailure(message: String) {
        Log.d("onPostFindRestaurantFailure",message)
    }

}