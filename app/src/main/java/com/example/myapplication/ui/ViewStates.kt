package com.example.myapplication.ui

import androidx.compose.ui.graphics.Color

sealed interface ItemsHeight {
    val heightPercent: Int

    data class Fixed(override val heightPercent: Int, val height: Int) : ItemsHeight
    data class Dynamic(override val heightPercent: Int) : ItemsHeight
}

data class ItemViewState(
    val title: String,
    val background: Color,
    val widthRatio: Float = 1F,
)

data class LayoutViewState(
    val items: List<ItemViewState>,
    val layoutBackground: Color,
    val columnsCount: Int,
    val itemsHeight: ItemsHeight,
)