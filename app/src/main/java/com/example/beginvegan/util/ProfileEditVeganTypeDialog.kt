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
import com.example.beginvegan.src.data.model.user.UserInterface
import com.example.beginvegan.src.data.model.user.UserVeganResponse
import com.example.beginvegan.src.data.model.user.UserVeganService

class ProfileEditVeganTypeDialog(context: Context, private val originalType:String): Dialog(context),
    UserInterface{
    private val binding: DialogProfileEidtVeganTypeBinding = DialogProfileEidtVeganTypeBinding.inflate(
        LayoutInflater.from(context))

    var selectedType:String? = null
    val TAG = "TAG"
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
        when(originalType){
            VeganTypes.VEGAN.veganType -> {binding.rbVegan.isChecked = true}
            VeganTypes.LACTO_VEGETARIAN.veganType -> {binding.rbLacto.isChecked = true}
            VeganTypes.LACTO_OVO_VEGETARIAN.veganType -> {binding.rbLactoOvo.isChecked = true}
            VeganTypes.PASCATARIAN.veganType -> {binding.rbPescatarian.isChecked = true}
            VeganTypes.POLLOTARIAN.veganType -> {binding.rbPollotarian.isChecked = true}
            VeganTypes.FLEXITARIAN.veganType -> {binding.rbFlexitarian.isChecked = true}
            "비기너" -> {binding.rbBeginner.isChecked = true}
        }
        //setOnChangeListener
        binding.rgEditVeganType.setOnCheckedChangeListener { group, checkedId ->
            when(checkedId){
                R.id.rb_vegan -> {selectedType = VeganTypes.VEGAN.veganType}
                R.id.rb_lacto -> {selectedType = VeganTypes.LACTO_VEGETARIAN.veganType}
                R.id.rb_lacto_ovo-> {selectedType = VeganTypes.LACTO_OVO_VEGETARIAN.veganType}
                R.id.rb_pescatarian -> {selectedType = VeganTypes.PASCATARIAN.veganType}
                R.id.rb_pollotarian -> {selectedType = VeganTypes.POLLOTARIAN.veganType}
                R.id.rb_flexitarian -> {selectedType = VeganTypes.FLEXITARIAN.veganType}
                R.id.rb_beginner -> {selectedType = "비기너"}
            }
        }
        //Cancel
        binding.btnCancel.setOnClickListener {
            this.dismiss()
        }
        //Confrim
        binding.btnConfirm.setOnClickListener {
            saveVagenType()
        }
    }
    private fun saveVagenType(){
        //저장
        if(!selectedType.isNullOrEmpty()){
            listener?.editVeganTypeOnSaveClicked(selectedType.toString())
        }
        //서버 - 유저
        val veganType = VeganTypes.values().find { it.veganType == selectedType}.toString() 
        UserVeganService(this).tryPostUserVeganType(veganType)
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
    //서버 - 유저
    override fun onPostUserVeganTypeSuccess(response: UserVeganResponse) {
        Log.d(TAG, "onPostUserVeganTypeSuccess: ")
    }

    override fun onPostUserVeganTypeFailure(message: String) {
        Log.d(TAG, "onPostUserVeganTypeFailure: $message")
    }
}