package com.example.beginvegan.src.data.model.magazine

interface MagazineInterface {
    fun onGetMagazineRandomListSuccess(response: MagazineTwoResponse)
    fun onGetMagazineRandomListFailure(message: String)

    fun onPostMagazineDetailSuccess(response: MagazineDetailResponse)
    fun onPostMagazineDetailFailure(message: String)
}