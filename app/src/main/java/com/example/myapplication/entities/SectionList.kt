package com.example.myapplication.entities

import com.squareup.moshi.Json

data class SectionList(
    @field:Json(name = "sections")
    val sections: List<Section>,
)
