package com.example.beginvegan.src.data.model.magazine

import com.example.beginvegan.config.ApplicationClass
import com.example.beginvegan.config.ErrorResponse
import com.example.beginvegan.src.data.api.MagazineRetrofitInterface
import com.example.beginvegan.util.Constants
import com.example.beginvegan.util.Constants.ACCESS_TOKEN
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

class MagazineService(val magazineInterface: MagazineInterface) {
    private val magazineRetrofitInterface: MagazineRetrofitInterface = ApplicationClass.sRetrofit.create(MagazineRetrofitInterface::class.java)
    private val accessToken = ("Bearer "+(ApplicationClass.xAccessToken))

    // 2가지 매거진 목록 조회
    fun tryGetMagazineTwoList(){
        magazineRetrofitInterface.getMagazineTwoList(accessToken).enqueue(object: Callback<MagazineTwoResponse>{
            override fun onResponse(
                call: Call<MagazineTwoResponse>,
                response: Response<MagazineTwoResponse>
            ) {
                if(response.code() == 200){
                    magazineInterface.onGetMagazineTwoListSuccess(response.body() as MagazineTwoResponse)
                }else{
                    try{
                        val gson = Gson()
                        val errorResponse =
                            gson.fromJson(response.errorBody()?.string(), ErrorResponse::class.java)
                        magazineInterface.onGetMagazineTwoListFailure(errorResponse.message)
                    }catch(e:Exception){
                        magazineInterface.onGetMagazineTwoListFailure(e.message?:"통신 오류")
                    }
                }
            }

            override fun onFailure(call: Call<MagazineTwoResponse>, t: Throwable) {
                magazineInterface.onGetMagazineTwoListFailure(t.message?:"통신 오류")
            }

        })
    }

    // 매거진 상세 정보 조회
    fun tryPostMagazineDetail(magazineId: Int){
        magazineRetrofitInterface.postMagazineDetail(accessToken,magazineId).enqueue(object: Callback<MagazineDetailResponse>{
            override fun onResponse(
                call: Call<MagazineDetailResponse>,
                response: Response<MagazineDetailResponse>
            ) {
                if(response.code() == 200){
                    magazineInterface.onPostMagazineDetailSuccess(response.body() as MagazineDetailResponse)
                }else{
                    try{
                        val gson = Gson()
                        val errorResponse =
                            gson.fromJson(response.errorBody()?.string(), ErrorResponse::class.java)
                        magazineInterface.onPostMagazineDetailFailure(errorResponse.message)
                    }catch(e:Exception){
                        magazineInterface.onPostMagazineDetailFailure(e.message?:"통신 오류")
                    }
                }
            }

            override fun onFailure(call: Call<MagazineDetailResponse>, t: Throwable) {
                magazineInterface.onPostMagazineDetailFailure(t.message?:"통신 오류")
            }

        })
    }

}