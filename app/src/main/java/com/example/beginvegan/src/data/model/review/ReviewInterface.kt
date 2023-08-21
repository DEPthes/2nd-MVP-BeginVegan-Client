package com.example.beginvegan.src.data.model.review

interface ReviewInterface {
    fun onPostWriteReviewSuccess(response: WriteReviewResponse)
    fun onPostWriteReviewFailure(message: String)

    fun onGetReviewListSuccess(response: ReviewListResponse)
    fun onGetReviewListAddSuccess(response:ReviewListResponse)
    fun onGetReviewListFailure(message: String)
}