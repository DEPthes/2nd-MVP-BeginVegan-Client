package com.example.beginvegan.util

import android.app.AlertDialog
import android.content.Context
import android.view.View
import android.widget.Toast
import com.example.beginvegan.src.ui.adapter.RecipeListRVAdapter
import net.daum.mf.map.api.MapPOIItem
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapView

class MarkerEventListener(val context: Context): MapView.POIItemEventListener {
    override fun onPOIItemSelected(mapView: MapView?, poiItem: MapPOIItem?) {

    }

    override fun onCalloutBalloonOfPOIItemTouched(mapView: MapView?, poiItem: MapPOIItem?) {
        // 말풍선 클릭 시 (Deprecated)
        // 이 함수도 작동하지만 그냥 아래 있는 함수에 작성하자
    }

    override fun onCalloutBalloonOfPOIItemTouched(mapView: MapView?, poiItem: MapPOIItem?, buttonType: MapPOIItem.CalloutBalloonButtonType?) {
        // 말풍선 클릭 시
        val builder = AlertDialog.Builder(context)
        val itemList = arrayOf("토스트", "마커 삭제", "취소")
        builder.setTitle("${poiItem?.itemName}")
        builder.setItems(itemList) { dialog, which ->
            when(which) {
                0 -> Toast.makeText(context, "토스트", Toast.LENGTH_SHORT).show()  // 토스트
                1 -> mapView?.removePOIItem(poiItem)    // 마커 삭제
                2 -> dialog.dismiss()   // 대화상자 닫기
            }
        }
        builder.show()
    }

    override fun onDraggablePOIItemMoved(mapView: MapView?, poiItem: MapPOIItem?, mapPoint: MapPoint?) {
        // 마커의 속성 중 isDraggable = true 일 때 마커를 이동시켰을 경우
    }
}