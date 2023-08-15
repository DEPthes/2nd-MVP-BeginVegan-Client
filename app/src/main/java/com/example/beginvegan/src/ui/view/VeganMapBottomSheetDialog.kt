//package com.example.beginvegan.src.ui.view
//
//import android.graphics.drawable.ColorDrawable
//import android.os.Bundle
//import android.view.Gravity
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import com.example.beginvegan.R
//import com.example.beginvegan.databinding.DialogBottomSheetBinding
//import com.google.android.material.bottomsheet.BottomSheetBehavior
//import com.google.android.material.bottomsheet.BottomSheetDialog
//import com.google.android.material.bottomsheet.BottomSheetDialogFragment
//
//class VeganMapBottomSheetDialog() : BottomSheetDialogFragment() {
//    private var _binding: DialogBottomSheetBinding? = null
//    private val binding get() = _binding!!
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        _binding = DialogBottomSheetBinding.inflate(inflater, container, false)
//        return binding.root
//    }
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        val dialog = dialog as BottomSheetDialog?
//        dialog!!.window!!.apply{
//            setBackgroundDrawable(ColorDrawable())
//            setDimAmount(0F)
//        }
//        // peekheight 기준 선정해야함
//        dialog.behavior.apply{
//            peekHeight = resources.getDimension(R.dimen.margin_76).toInt()
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
//    }
//}