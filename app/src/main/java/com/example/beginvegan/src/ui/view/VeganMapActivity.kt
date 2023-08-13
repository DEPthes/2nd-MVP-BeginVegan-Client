package com.example.beginvegan.src.ui.view

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.location.LocationManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.view.Window
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.beginvegan.R
import com.example.beginvegan.config.BaseActivity
import com.example.beginvegan.databinding.ActivityVeganMapBinding
import com.example.beginvegan.util.Constants.ACCESS_FINE_LOCATION
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import net.daum.mf.map.api.MapPOIItem
import net.daum.mf.map.api.MapView

class VeganMapActivity : BaseActivity<ActivityVeganMapBinding>({ActivityVeganMapBinding.inflate(it)}) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setBottomSheet()
    }

    override fun init() {
        if (checkLocationService()) {
            permissionCheck()
        } else {
            Toast.makeText(this, "GPS를 켜주세요", Toast.LENGTH_SHORT).show()
        }
    }
    // Set Bottom Sheet
    private fun setBottomSheet(){
        val bottomSheet = VeganMapBottomSheetDialog()
//        bottomSheet.behavior.isDraggable = true
//        bottomSheet.behavior.peekHeight = resources.getDimension(R.dimen.margin_76).toInt()
        bottomSheet.show(supportFragmentManager,bottomSheet.tag)

//        val bottomSheetView = layoutInflater.inflate(R.layout.dialog_bottom_sheet, null)
//        val bottomSheetDialog = BottomSheetDialog(this)
//        bottomSheetDialog.setContentView(bottomSheetView)
//        bottomSheetDialog.window!!.setDimAmount(0F)
//        bottomSheetDialog.behavior.isDraggable = true
//        bottomSheet
//        // peekHeight 바텀시트 접혀있을때 DP 설정
//        bottomSheetDialog.behavior.peekHeight = resources.getDimension(R.dimen.margin_76).toInt()
//        bottomSheetDialog.show()
//
//        bottomSheetDialog.behavior.apply{
//            addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
//                override fun onStateChanged(bottomSheet: View, newState: Int) {
//                    when(newState){
//                        BottomSheetBehavior.STATE_COLLAPSED -> {}
//                        BottomSheetBehavior.STATE_EXPANDED -> {}
//                        BottomSheetBehavior.STATE_HIDDEN -> {}    //숨겨짐
//                        BottomSheetBehavior.STATE_HALF_EXPANDED -> {} //절반 펼쳐짐
//                        BottomSheetBehavior.STATE_DRAGGING -> {}  //드래그하는 중
//                        BottomSheetBehavior.STATE_SETTLING -> {}  //(움직이다가) 안정화되는 중
//                    }
//                }
//                override fun onSlide(bottomSheet: View, slideOffset: Float) {
//                }
//            })
//        }


    }


    // 권한에 대한 메소드
    private fun permissionCheck() {
        val preference = getPreferences(MODE_PRIVATE)
        val isFirstCheck = preference.getBoolean("isFirstPermissionCheck", true)
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // 권한이 없는 상태
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
                // 권한 거절
                val builder = AlertDialog.Builder(this)
                builder.setMessage("현재 위치를 확인하시려면 위치 권한을 허용해주세요.")
                builder.setPositiveButton("확인") { dialog, which ->
                    ActivityCompat.requestPermissions(this,
                        arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), ACCESS_FINE_LOCATION)
                }
                builder.setNegativeButton("취소") { dialog, which ->

                }
                builder.show()
            } else {
                if (isFirstCheck) {
                    // 최초 권한 요청
                    preference.edit().putBoolean("isFirstPermissionCheck", false).apply()
                    ActivityCompat.requestPermissions(this,
                        arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), ACCESS_FINE_LOCATION)
                } else {
                    val builder = AlertDialog.Builder(this)
                    builder.setMessage("현재 위치를 확인하시려면 설정에서 위치 권한을 허용해주세요.")
                    builder.setPositiveButton("설정으로 이동") { dialog, which ->
                        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.parse("package:$packageName"))
                        startActivity(intent)
                    }
                    builder.setNegativeButton("취소") { dialog, which ->

                    }
                    builder.show()
                }
            }
        } else {
            // 권한 있는 상태
            startTracking()

        }

    }

    // 권한 요청
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == ACCESS_FINE_LOCATION) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "위치 권한이 승인되었습니다", Toast.LENGTH_SHORT).show()
                startTracking()
            } else {
                Toast.makeText(this, "위치 권한이 거절되었습니다", Toast.LENGTH_SHORT).show()

            }
        }
    }

    // GPS가 켜져있는지 확인
    private fun checkLocationService(): Boolean {
        val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
    }

    private fun startTracking(){
        binding.mvVeganMap.currentLocationTrackingMode = MapView.CurrentLocationTrackingMode.TrackingModeOnWithoutHeading
        binding.mvVeganMap.setCustomCurrentLocationMarkerTrackingImage(R.drawable.test_location_small, MapPOIItem.ImageOffset(25, 25))
    }
}