package com.example.myapplication.entities

import com.squareup.moshi.Json

data class Category(@field:Json(name = "title")
                    val title: String,
                    @field:Json(name = "color")
                    val color: String)
