package com.example.beginvegan.util

import com.example.beginvegan.src.data.model.user.VeganType

enum class VeganTypes(val veganType:String) {
    VEGAN("비건"),
    LACTO_VEGETARIAN("락토 베지테리언"),
    LACTO_OVO_VEGETARIAN("락토-오보 베지테리언"),
    PASCATARIAN("페스코 베지테리언"),
    POLLOTARIAN("폴로 베지테리언"),
    FLEXITARIAN("플렉시테리언");
}
