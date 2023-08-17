package com.example.beginvegan.src.data.model.magazines

import com.google.gson.annotations.SerializedName

data class MagazineTwoResponse(
    // ENUM TYPE?
    @SerializedName("magazineType") val magazineType: String
):Magazine()
