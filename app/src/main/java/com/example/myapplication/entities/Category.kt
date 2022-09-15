package com.example.myapplication.entities

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Category(
    @Json(name = "title")
    val title: String,
    @Json(name = "color")
    val color: String,
)
