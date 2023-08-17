package com.example.beginvegan.src.data.model.magazine

import com.example.beginvegan.config.ApplicationClass
import com.example.beginvegan.config.ErrorResponse
import com.example.beginvegan.src.data.api.MagazineRetrofitInterface
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

class MagazineService(val magazineInterface: MagazineInterface) {
    private val magazineRetrofitInterface: MagazineRetrofitInterface = ApplicationClass.sRetrofit.create(MagazineRetrofitInterface::class.java)

    fun tryGetMagazineRandomList(){
        magazineRetrofitInterface.getMagazineTwoList().enqueue(object: Callback<MagazineTwoResponse>{
            override fun onResponse(
                call: Call<MagazineTwoResponse>,
                response: Response<MagazineTwoResponse>
            ) {
                if(response.code() == 200){
                    magazineInterface.onGetMagazineRandomListSuccess(response.body() as MagazineTwoResponse)
                }else{
                    try{
                        val gson = Gson()
                        val errorResponse =
                            gson.fromJson(response.errorBody()?.string(), ErrorResponse::class.java)
                        magazineInterface.onGetMagazineRandomListFailure(errorResponse.message)
                    }catch(e:Exception){
                        magazineInterface.onGetMagazineRandomListFailure(e.message?:"통신 오류")
                    }
                }
            }

            override fun onFailure(call: Call<MagazineTwoResponse>, t: Throwable) {
                magazineInterface.onGetMagazineRandomListFailure(t.message?:"통신 오류")
            }

        })
    }
    fun tryPostMagazineDetail(magazineId: Int){
        magazineRetrofitInterface.postMagazineDetail(magazineId).enqueue(object: Callback<MagazineDetailResponse>{
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