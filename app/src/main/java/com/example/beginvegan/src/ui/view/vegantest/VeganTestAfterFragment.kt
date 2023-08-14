package com.example.beginvegan.src.ui.view.vegantest

import android.os.Bundle
import android.view.View
import com.example.beginvegan.R
import com.example.beginvegan.config.BaseFragment
import com.example.beginvegan.databinding.FragmentVeganTestAfterBinding

class VeganTestAfterFragment : BaseFragment<FragmentVeganTestAfterBinding>(
    FragmentVeganTestAfterBinding::bind,R.layout.fragment_vegan_test_after) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments.let{
            binding.tvVeganTypeKr.text = it?.getString(TYPE_KR)
//            binding.tvVeganTypeEng.text = typeEng
//            binding.tvVeganTypeDescription.text = description
        }


        binding.btnVeganTestAgain.setOnClickListener{
            val veganTestBeforeFragment = VeganTestBeforeFragment.newInstance()
            (activity as VeganTestActivity).changeTestState(veganTestBeforeFragment)
        }
        binding.btnGoHome.setOnClickListener {
            (activity as VeganTestActivity).goHome()
        }
    }

    companion object{
        private const val TYPE_KR = "typeKr"
        private const val TYPE_ENG = "typeEng"
        private const val DESCRIPTION = "description"
        fun newInstance(typeKr:String, typeEng:String, description:String): VeganTestAfterFragment {
            val fragment = VeganTestAfterFragment()
            val bundle = Bundle()
            bundle.putString(TYPE_KR,typeKr)
            bundle.putString(TYPE_ENG,typeEng)
            bundle.putString(DESCRIPTION,description)
            fragment.arguments = bundle
            return fragment
        }
    }

//    fun setTestResult(typeKr:String, typeEng:String, description:String){
//        binding.tvVeganTypeKr.text = typeKr
//        binding.tvVeganTypeEng.text = typeEng
//        binding.tvVeganTypeDescription.text = description
//    }
}