package com.example.myapplication.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Cyan
import androidx.compose.ui.graphics.Color.Companion.Magenta
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HorizontalScrollCompose : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val mainViewModel: MainActivityViewModel = viewModel()

            HorizontalScroll(
                itemsCount = 10,
                columnsCount = 3,
                itemsHeight = 100,
                layoutWidth = 300,
                itemsHeightPercent = 20,
                parentPaddingStart = 15,
                parentPaddingEnd = 15,
                parentPaddingTop = 15,
                parentPaddingBottom = 15,
                itemDecorationHorizontal = 10,
                hasDinamycHeight = false
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    HorizontalScroll(
        itemsCount = 10,
        columnsCount = 3,
        itemsHeight = 100,
        layoutWidth = 300,
        itemsHeightPercent = 20,
        parentPaddingStart = 20,
        parentPaddingEnd = 15,
        parentPaddingTop = 15,
        parentPaddingBottom = 15,
        itemDecorationHorizontal = 10,
        hasDinamycHeight = false
    )
}

@Composable
fun HorizontalScroll(
    itemsCount: Int,
    columnsCount: Int,
    itemsHeight: Int,
    layoutWidth: Int,
    itemsHeightPercent: Int,
    parentPaddingStart: Int,
    parentPaddingEnd: Int,
    parentPaddingTop: Int,
    parentPaddingBottom: Int,
    itemDecorationHorizontal: Int,
    hasDinamycHeight: Boolean = true,
) {
    val itemWidth =
        ((layoutWidth - parentPaddingStart - itemDecorationHorizontal * columnsCount) / (columnsCount + 0.5)).toInt()

    var itemHeight = itemsHeight
    if (hasDinamycHeight) {
        itemHeight = layoutWidth * itemsHeightPercent / 100
    }

    LazyRow(
        modifier = Modifier
            .width(layoutWidth.dp)
            .background(Cyan),
        contentPadding = PaddingValues(
            start = parentPaddingStart.dp,
            end = parentPaddingEnd.dp,
            top = parentPaddingTop.dp,
            bottom = parentPaddingBottom.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(itemDecorationHorizontal.dp),
    ) {
        items(itemsCount) { data ->
            Box(
                modifier = Modifier
                    .width(itemWidth.dp)
                    .background(color = Magenta)
                    .height(itemHeight.dp)
                    .border(width = 1.dp, color = Black)
            )
        }
    }
}