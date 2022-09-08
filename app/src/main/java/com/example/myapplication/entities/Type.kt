package com.example.myapplication.entities

import com.squareup.moshi.Json

data class Type(@field:Json(name = "name")
                    val name: String,
                @field:Json(name = "columns")
                    val columns: Int)
