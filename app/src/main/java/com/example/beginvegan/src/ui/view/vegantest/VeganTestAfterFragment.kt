package com.example.beginvegan.src.ui.view.vegantest

import android.util.Log
import com.example.beginvegan.R
import com.example.beginvegan.config.BaseFragment
import com.example.beginvegan.databinding.FragmentVeganTestAfterBinding
import com.example.beginvegan.src.data.model.user.UserInterface
import com.example.beginvegan.src.data.model.user.UserVeganResponse
import com.example.beginvegan.src.data.model.user.UserVeganService
import com.example.beginvegan.util.VeganType

class VeganTestAfterFragment : BaseFragment<FragmentVeganTestAfterBinding>(
    FragmentVeganTestAfterBinding::bind,R.layout.fragment_vegan_test_after),
    UserInterface{
    val TAG = "tag"
    var veganTypes = ""
    override fun init() {
        val testActivity =(activity as VeganTestActivity)
        val veganTestBeforeFragment = VeganTestBeforeFragment.newInstance()
        //서버 - 유저
        val userVeganType = testActivity.getUserType()
        veganTypes = (VeganType.values().find { it.veganType == userVeganType}).toString()
        UserVeganService(this).tryPostUserVeganType(veganTypes)

        //테스트 결과 받아와서 띄워주기
        binding.tvVeganTypeKr.setText(userVeganType)
        binding.tvVeganTypeEng.setText(testActivity.getTypeEng())
        binding.tvVeganTypeDescription.setText(testActivity.getDescription())

        binding.btnVeganTestAgain.setOnClickListener{
            testActivity.changeTestState(veganTestBeforeFragment)
            testActivity.resetUserType()
        }
        binding.btnGoHome.setOnClickListener {
            testActivity.goHome()
        }
    }

    companion object {
        fun newInstance(): VeganTestAfterFragment {
            return VeganTestAfterFragment()
        }
    }

    //서버 - 유저
    override fun onPostUserVeganTypeSuccess(response: UserVeganResponse) {
        Log.d(TAG, "onPostUserVeganTypeSuccess: $veganTypes")
    }

    override fun onPostUserVeganTypeFailure(message: String) {
        Log.d(TAG, "onPostUserVeganTypeFailure: $message")
    }
}