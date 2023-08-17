package com.example.beginvegan.src.data.model.magazines

import com.google.gson.annotations.SerializedName

data class MagazineDetailResponse(
    @SerializedName("source") val source: String,
    // ENUM TYPE?
    @SerializedName("magazineType") val magazineType: String,
    @SerializedName("magazineContents") val magazineContents: List<MagazineContents>
): Magazine()
