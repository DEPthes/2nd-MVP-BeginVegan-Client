package com.example.beginvegan.src.data.model.magazine

interface MagazineInterface {
    fun onGetMagazineTwoListSuccess(response: MagazineTwoResponse)
    fun onGetMagazineTwoListFailure(message: String)

    fun onPostMagazineDetailSuccess(response: MagazineDetailResponse)
    fun onPostMagazineDetailFailure(message: String)
}