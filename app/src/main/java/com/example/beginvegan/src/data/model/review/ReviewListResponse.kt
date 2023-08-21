package com.example.beginvegan.src.data.model.review

import com.example.beginvegan.config.BaseResponse
import com.google.gson.annotations.SerializedName

data class ReviewListResponse(
    @SerializedName("information") val information: Review
):BaseResponse()
