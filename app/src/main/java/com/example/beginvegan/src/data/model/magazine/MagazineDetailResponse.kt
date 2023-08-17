package com.example.beginvegan.src.data.model.magazine

import com.google.gson.annotations.SerializedName

data class MagazineDetailResponse(
    @SerializedName("source") val source: String,
    // ENUM TYPE?
    @SerializedName("magazineType") val magazineType: String,
    @SerializedName("magazineContents") val magazineContents: List<MagazineContents>
): Magazine()
