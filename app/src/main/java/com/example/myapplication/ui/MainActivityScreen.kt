package com.example.myapplication.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.graphics.Color.Companion.Magenta
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.Color.Companion.Yellow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlin.random.Random.Default.nextInt

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    val colors = listOf(Gray, Red, Green, Yellow, Magenta)
    val items = mutableListOf<ItemViewState>()
    for (i in 0..21) {
        val colorId = nextInt(0, 5)
        items.add(ItemViewState("title $i", background = colors.get(colorId)))
    }

    val gridViewState =
        GridViewState(items, columnsCount = 4, gridBackground = Color.Cyan, itemsHeight = ItemsHeight.Dynamic(20))

    FlexibleGrid(
        gridViewState = gridViewState,
        gridWidth = 300,
        paddingValues = PaddingValues(start = 15.dp, end = 15.dp, top = 15.dp, bottom = 15.dp),
        verticalArrangement = Arrangement.spacedBy(15.dp),
        horizontalArrangement = Arrangement.spacedBy(15.dp)
    )
}

@Composable
fun FlexibleGrid(
    gridViewState: GridViewState,
    gridWidth: Int,
    paddingValues: PaddingValues,
    verticalArrangement: Arrangement.Vertical,
    horizontalArrangement: Arrangement.Horizontal,
) {
    val itemsCount = gridViewState.items.size
    val columnsCount = gridViewState.columnsCount
    var itemsGrid = itemsCount
    var itemsInRow = 0
    if (itemsCount > columnsCount) {
        itemsInRow = itemsCount % columnsCount
        if (itemsInRow != 0) {
            itemsGrid = itemsCount - itemsInRow
        }
    }

    val itemsHeight = gridViewState.itemsHeight
    var itemHeight = 0
    when (itemsHeight) {
        is ItemsHeight.Fixed -> itemHeight = itemsHeight.height
        is ItemsHeight.Dynamic -> itemHeight = gridWidth * (itemsHeight.heightPercent) / 100
    }

    LazyVerticalGrid(
        columns = GridCells.Fixed(columnsCount),
        modifier = Modifier.width(gridWidth.dp).background(gridViewState.gridBackground),
        contentPadding = paddingValues,
        verticalArrangement = verticalArrangement,
        horizontalArrangement = horizontalArrangement,
    ) {
        items(itemsGrid, span = { GridItemSpan(1) }) { index ->
            val item = gridViewState.items.get(index)
            Box(
                modifier = Modifier
                    .height(itemHeight.dp)
                    .fillMaxWidth()
                    .background(color = item.background)
            ) {
                Text(text = item.title)
            }
        }

        items(1, span = { GridItemSpan(columnsCount) }) {
            Row(modifier = Modifier.fillMaxSize(), horizontalArrangement = horizontalArrangement) {
                for (i in itemsInRow downTo 0) {
                    val item = gridViewState.items.get(gridViewState.items.size - 1 - i)
                    Box(
                        modifier = Modifier
                            .weight(1F) // No width specified here because weight controls it
                            .height(itemHeight.dp)
                            .background(color = item.background)
                    ) {
                        Text(text = item.title)
                    }
                }
            }
        }
    }
}




