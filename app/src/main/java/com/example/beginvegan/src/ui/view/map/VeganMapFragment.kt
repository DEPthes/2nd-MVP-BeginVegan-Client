package com.example.beginvegan.src.ui.view.map

import android.app.Activity
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.beginvegan.R
import com.example.beginvegan.config.ApplicationClass
import com.example.beginvegan.config.BaseFragment
import com.example.beginvegan.databinding.FragmentVeganMapBinding
import com.example.beginvegan.src.data.model.recipe.RecipeThree
import com.example.beginvegan.src.data.model.restaurant.Coordinate
import com.example.beginvegan.src.data.model.restaurant.NearRestaurant
import com.example.beginvegan.src.data.model.restaurant.RestaurantFindInterface
import com.example.beginvegan.src.data.model.restaurant.RestaurantFindResponse
import com.example.beginvegan.src.data.model.restaurant.RestaurantFindService
import com.example.beginvegan.src.ui.adapter.map.VeganMapBottomSheetRVAdapter
import com.example.beginvegan.src.ui.view.main.MainActivity
import com.example.beginvegan.src.ui.view.map.restaurant.RestaurantDetailFragment
import com.example.beginvegan.util.Constants
import com.example.beginvegan.util.Constants.RECOMMENDED_RESTAURANT
import com.example.beginvegan.util.Constants.RESTAURANT_ID
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_COLLAPSED
import com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_HALF_EXPANDED
import net.daum.mf.map.api.MapPOIItem
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapView

/*
* 추천 식당 클릭후 어댑터 연결 문제
* */

class VeganMapFragment : BaseFragment<FragmentVeganMapBinding>(
    FragmentVeganMapBinding::bind,
    R.layout.fragment_vegan_map
), RestaurantFindInterface, MapView.POIItemEventListener {
    private lateinit var dataList: ArrayList<NearRestaurant>
    private lateinit var mapView: MapView
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>
    private lateinit var bottomSheetAdapter: VeganMapBottomSheetRVAdapter
    private lateinit var recommendRestaurantData: NearRestaurant
    private var recommendRestaurantTrigger = true
    private var mContext: Context? = null

    // Android Lifecycle
    override fun onPause() {
        super.onPause()
        binding.mvVeganMap.removeAllViews()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // case: Recommend restaurant click
        if (arguments != null) {
            val data = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                arguments?.getSerializable(RECOMMENDED_RESTAURANT, NearRestaurant::class.java)
            } else {
                arguments?.getSerializable(RECOMMENDED_RESTAURANT) as? NearRestaurant
            }
            if (data != null) {
                recommendRestaurantData = data
                recommendRestaurantTrigger = false
            }
        }
    }

    override fun onDetach() {
        super.onDetach()
        mContext = null
    }

    override fun init() {
        showLoadingDialog(requireContext())
        initializeMapView()
        binding.veganmapBottomSheet.clBottomSheet.maxHeight = getBottomSheetDialogDefaultHeight()
        RestaurantFindService(this).tryPostFindRestaurant(
            Coordinate(
                ApplicationClass.xLatitude,
                ApplicationClass.xLongitude
            )
        )
    }

    // Initialize MapView & MapView Click
    private fun initializeMapView() {
        mapView = MapView(this@VeganMapFragment.activity)
        binding.mvVeganMap.addView(mapView)
        bottomSheetBehavior = BottomSheetBehavior.from(binding.veganmapBottomSheet.clBottomSheet)
        mapView.setMapCenterPointAndZoomLevel(
            MapPoint.mapPointWithGeoCoord(
                ApplicationClass.xLatitude.toDouble(),
                ApplicationClass.xLongitude.toDouble()
            ), 4, true
        )
        mapView.currentLocationTrackingMode =
            MapView.CurrentLocationTrackingMode.TrackingModeOnWithHeadingWithoutMapMoving
        mapView.setOnTouchListener { _, _ ->
            if (bottomSheetBehavior.state != STATE_COLLAPSED) {
                bottomSheetBehavior.state = STATE_COLLAPSED
            }
            false
        }

    }

    // BottomSheet Sizing | Height 70%
    private fun getBottomSheetDialogDefaultHeight(): Int {
        return getWindowHeight() * 70 / 100
        // 위 수치는 기기 높이 대비 70%로 높이를 설정
    }

    private fun getWindowHeight(): Int {
        val displayMetrics = DisplayMetrics()
        (context as Activity?)!!.windowManager.defaultDisplay.getMetrics(displayMetrics)
        return displayMetrics.heightPixels
    }

    // Restaurant's pin setting and connect MapView
    private fun setMapViewRestaurantMarker() {
        dataList.forEachIndexed { index, info ->
            val marker = MapPOIItem().apply {
                itemName = info.name
                mapPoint = MapPoint.mapPointWithGeoCoord(
                    info.latitude.toDouble(),
                    info.longitude.toDouble()
                )
                userObject = dataList[index]
                markerType = MapPOIItem.MarkerType.CustomImage
                tag = index
                customImageResourceId = R.drawable.marker_spot
                isShowCalloutBalloonOnTouch = false
            }
            mapView.addPOIItem(marker)
        }
        mapView.setPOIItemEventListener(this)
    }


    private fun setBottomSheetRVAdapter() {
        binding.veganmapBottomSheet.rvBottomSheetRestaurantList.adapter = bottomSheetAdapter
        binding.veganmapBottomSheet.rvBottomSheetRestaurantList.layoutManager =
            LinearLayoutManager(mContext)
        bottomSheetAdapter.setOnItemClickListener(object :
            VeganMapBottomSheetRVAdapter.OnItemClickListener {
            override fun onItemClick(v: View, data: NearRestaurant, position: Int) {
                moveRestaurantDetail(data)
            }
        })
        bottomSheetBehavior.state = STATE_HALF_EXPANDED
    }

    private fun setAdapterBottomSheet() {
        bottomSheetAdapter = VeganMapBottomSheetRVAdapter(mContext!!, dataList)
        setBottomSheetRVAdapter()
    }

    private fun setAdapterSingleBottomSheet(data: NearRestaurant) {
        var selectedRestaurant: ArrayList<NearRestaurant> = arrayListOf()
        selectedRestaurant.add(data)
        bottomSheetAdapter = VeganMapBottomSheetRVAdapter(mContext!!, selectedRestaurant)
        mapView.setMapCenterPoint(
            MapPoint.mapPointWithGeoCoord(
                data.latitude.toDouble(),
                data.longitude.toDouble()
            ), true
        )
        setBottomSheetRVAdapter()
    }

    private fun moveRestaurantDetail(data: NearRestaurant) {
        parentFragmentManager.setFragmentResult(RESTAURANT_ID, bundleOf(RESTAURANT_ID to data.id))
        parentFragmentManager.beginTransaction().hide(this@VeganMapFragment)
            .add(R.id.fl_main, RestaurantDetailFragment()).addToBackStack(null).commit()
    }

    override fun onPOIItemSelected(p0: MapView?, p1: MapPOIItem?) {
        setAdapterSingleBottomSheet(p1?.userObject as NearRestaurant)
    }

    override fun onCalloutBalloonOfPOIItemTouched(p0: MapView?, p1: MapPOIItem?) {}
    override fun onCalloutBalloonOfPOIItemTouched(
        p0: MapView?,
        p1: MapPOIItem?,
        p2: MapPOIItem.CalloutBalloonButtonType?
    ) {
    }

    override fun onDraggablePOIItemMoved(p0: MapView?, p1: MapPOIItem?, p2: MapPoint?) {}


    override fun onPostFindRestaurantSuccess(response: RestaurantFindResponse) {
        dataList = ArrayList(response.information)
        setMapViewRestaurantMarker()
        if (recommendRestaurantTrigger) {
            setAdapterBottomSheet()
        } else {
            setAdapterSingleBottomSheet(recommendRestaurantData)
        }
        dismissLoadingDialog()
    }

    override fun onPostFindRestaurantFailure(message: String) {
        Log.d("onPostFindRestaurantFailure", message)
    }


}