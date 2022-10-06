package com.example.myapplication.ui

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight

data class TitleViewState(
    val title: String,
    val layoutMarginTop: Int,
    val layoutMarginBottom: Int,
    val fontSize: Int,
    val fontWeight: FontWeight,
    val titleBackground: Color,
    val textColor: Color,
)
