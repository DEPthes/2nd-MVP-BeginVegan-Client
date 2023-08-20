package com.example.beginvegan.src.data.model.magazine

import com.example.beginvegan.config.BaseResponse
import com.google.gson.annotations.SerializedName

data class MagazineDetailResponse(
    @SerializedName("information") val information: MagazineDetail
):BaseResponse()
