package com.example.beginvegan.util

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.WindowManager
import com.example.beginvegan.R
import com.example.beginvegan.databinding.DialogProfileEidtVeganTypeBinding

class ProfileEditVeganTypeDialog(context: Context, private val originalType:String): Dialog(context) {
    private val binding: DialogProfileEidtVeganTypeBinding = DialogProfileEidtVeganTypeBinding.inflate(
        LayoutInflater.from(context))

    var selectedId:Int = 6
    val veganTypeListKr = listOf("비건","락토 베지테리언","락토-오보 베지테리언","페스코 베지테리언","폴로 베지테리언","플렉시테리언")
    val veganTypeListEng = listOf("VEGAN", "LACTO_VEGETARIAN", "OVO_VEGETARIAN", "LACTO_OVO_VEGETARIAN", "POLLOTARIAN", "PASCATARIAN", "FLEXITARIAN")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        //dialog 크기
        val layoutParams = WindowManager.LayoutParams()
        layoutParams.copyFrom(window!!.attributes)
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT
        window!!.attributes = layoutParams

        //기존 유형 선택된 상태로 표시하기
        val index = veganTypeListKr.indexOf(originalType)
        when(index){
            0 -> {binding.rbVegan.isChecked = true}
            1 -> {binding.rbLacto.isChecked = true}
            2 -> {binding.rbLactoOvo.isChecked = true}
            3 -> {binding.rbPescatarian.isChecked = true}
            4 -> {binding.rbPollotarian.isChecked = true}
            5 -> {binding.rbFlexitarian.isChecked = true}
        }
        //setOnChangeListener
        binding.rgEditVeganType.setOnCheckedChangeListener { group, checkedId ->
            when(checkedId){
                R.id.rb_vegan -> {selectVeganType(0)}
                R.id.rb_lacto -> {selectVeganType(1)}
                R.id.rb_lacto_ovo-> {selectVeganType(2)}
                R.id.rb_pescatarian -> {selectVeganType(3)}
                R.id.rb_pollotarian -> {selectVeganType(4)}
                R.id.rb_flexitarian -> {selectVeganType(5)}
            }

        }
        //Cancel
        binding.btnCancel.setOnClickListener {
            this.dismiss()
        }
        //Confrim
        binding.btnConfirm.setOnClickListener {
            saveVagenType(selectedId)
        }
    }

    private fun selectVeganType(checkedId:Int){
        //클릭한 정보 저장
        selectedId = checkedId
    }
    private fun saveVagenType(checkedId:Int){
        //저장
        if(checkedId!=6){
            listener?.editVeganTypeOnSaveClicked(veganTypeListKr[checkedId])
        }

        //서버 저장
//        veganTypeListEng[checkedId]
        this.dismiss()
    }

    //인터페이스
    interface EditVeganTypeDialogListener {
        fun editVeganTypeOnSaveClicked(type: String)
    }
    private var listener: EditVeganTypeDialogListener? = null

    fun setListener(listener: EditVeganTypeDialogListener) {
        this.listener = listener
    }
}