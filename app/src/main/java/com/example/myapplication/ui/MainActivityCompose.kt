package com.example.myapplication.ui


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.Magenta
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivityCompose : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val mainViewModel: MainActivityViewModel = viewModel()

            FlexibleGird(
                itemsCount = 10,
                columnsCount = 3,
                itemsHeight = 100,
                parentPaddingStart = 5,
                parentPaddingEnd = 5,
                parentPaddingTop = 5,
                parentPaddingBottom = 5,
                itemDecorationVertical = 5,
                itemDecorationHorizontal = 5,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    FlexibleGird(
        itemsCount = 10,
        columnsCount = 3,
        itemsHeight = 100,
        parentPaddingStart = 5,
        parentPaddingEnd = 5,
        parentPaddingTop = 5,
        parentPaddingBottom = 5,
        itemDecorationVertical = 5,
        itemDecorationHorizontal = 5,
    )
}

@Composable
fun FlexibleGird(
    itemsCount: Int,
    columnsCount: Int,
    itemsHeight: Int,
    parentPaddingStart: Int,
    parentPaddingEnd: Int,
    parentPaddingTop: Int,
    parentPaddingBottom: Int,
    itemDecorationVertical: Int,
    itemDecorationHorizontal: Int,
) {
    val itemsInRow = itemsCount % columnsCount
    var itemsGrid = itemsCount
    if (itemsInRow != 0) {
        itemsGrid = itemsCount - itemsInRow
    }

    LazyVerticalGrid(
        columns = GridCells.Fixed(columnsCount),
        contentPadding = PaddingValues(
            start = parentPaddingStart.dp,
            end = parentPaddingEnd.dp,
            top = parentPaddingTop.dp,
            bottom = parentPaddingBottom.dp),
        verticalArrangement = Arrangement.spacedBy(itemDecorationVertical.dp),
        horizontalArrangement = Arrangement.spacedBy(itemDecorationHorizontal.dp)
    ) {
        items(itemsGrid, span = { GridItemSpan(1) }) {
            Box(
                modifier = Modifier
                    .height(itemsHeight.dp)
                    .fillMaxWidth()
                    .border(BorderStroke(5.dp, Blue))
                    .background(Gray)
            )
        }

        items(1, span = { GridItemSpan(columnsCount) }) {
            Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxSize()) {
                for (i in 0 until itemsInRow) {
                    Box(
                        modifier = Modifier
                            .weight(1F) // No width specified here because weight controls it
                            .background(color = Magenta)
                            .height(itemsHeight.dp)
                            .border(width = 5.dp, color = Black)
                    )
                }
            }
        }
    }
}