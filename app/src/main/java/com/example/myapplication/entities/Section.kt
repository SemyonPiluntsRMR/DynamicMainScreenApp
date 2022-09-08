package com.example.myapplication.entities

import com.squareup.moshi.Json

data class Section(
    @field:Json(name = "title")
    val title: String,
    @field:Json(name = "titleColor")
    val titleColor: String,
    @field:Json(name = "type")
    val type: Type,
    @field:Json(name = "backgroundColor")
    val backgroundColor: String,
    @field:Json(name = "categories")
    val categories: List<Category>
)
