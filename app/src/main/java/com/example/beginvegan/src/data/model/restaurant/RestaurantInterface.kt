package com.example.beginvegan.src.data.model.restaurant

interface RestaurantInterface {

    // 식당/카페 상세 정보(메뉴 까지) 조회
    fun onGetRestaurantDetailSuccess(response:RestaurantDetailResponse)
    fun onGetRestaurantDetailFailure(message: String)


    // 식당/카페 리뷰 조회
    fun onGetRestaurantReviewSuccess(response: RestaurantReviewResponse)
    fun onGetRestaurantReviewFailure(message: String)

    // 식당/카페 스크랩
    fun onPostScrapRestaurantSuccess(response: RestaurantScrapResponse)
    fun onPostScrapRestaurantFailure(message: String)

    // 식당/카페 스크랩 해제
    fun onPostScrapDeleteRestaurantSuccess(response: RestaurantScrapDeleteResponse)
    fun onPostScrapDeleteRestaurantFailure(message: String)

}