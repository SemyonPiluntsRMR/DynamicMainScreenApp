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
)

data class GridViewState(
    val items: List<ItemViewState>,
    val gridBackground: Color,
    val columnsCount: Int,
    val itemsHeight: ItemsHeight,
)