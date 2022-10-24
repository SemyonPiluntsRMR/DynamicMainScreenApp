package com.example.myapplication.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.graphics.Color.Companion.Magenta
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.Color.Companion.Yellow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import kotlin.random.Random.Default.nextInt
import com.example.myapplication.R

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    val colors = listOf(Gray, Red, Green, Yellow, Magenta)
    val items = mutableListOf<ItemViewState>()
    for (i in 0..21) {
        val colorId = nextInt(0, 5)
        items.add(ItemViewState("title $i", background = colors.get(colorId)))
    }

    val layoutViewState =
        LayoutViewState(items, columnsCount = 4, layoutBackground = Color.Cyan, itemsHeight = ItemsHeight.Dynamic(20))

//    FlexibleGrid(
//        layoutViewState = layoutViewState,
//        gridWidth = 300,
//        paddingValues = PaddingValues(start = 15.dp, end = 15.dp, top = 15.dp, bottom = 15.dp),
//        verticalArrangement = Arrangement.spacedBy(15.dp),
//        horizontalArrangement = Arrangement.spacedBy(15.dp)
//    )
//
//    HorizontalScroll(
//        layoutViewState = layoutViewState,
//        layoutWidth = 300,
//        paddingValues = PaddingValues(start = 15.dp, end = 15.dp, top = 15.dp, bottom = 15.dp),
//        horizontalArrangement = Arrangement.spacedBy(15.dp)
//    )

    WidgetItem(
        layoutHeight = 200.dp,
        layoutWidth = 300.dp,
        paddingValues = PaddingValues(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 16.dp),
        alignment = Alignment.BottomEnd,
        title = "MyTitle",
        titleAlignment = Alignment.TopStart,
        titleFontSize = 28.sp,
        titleColor = Color.Yellow,
        subTitle = "MySubtitle MySubtitle MySubtitle MySubtitle MySubtitle MySubtitle MySubtitle",
        subTitleAlignment = Alignment.BottomStart,
        subTitleFontSize = 18.sp,
        subTitleColor = Color.Green,
    )
}

@Composable
fun WidgetItem(
    layoutWidth: Dp,
    layoutHeight: Dp,
    paddingValues: PaddingValues,
    alignment: Alignment,
    title: String,
    titleAlignment: Alignment,
    titleFontSize: TextUnit,
    titleColor: Color,
    subTitle: String,
    subTitleAlignment: Alignment,
    subTitleFontSize: TextUnit,
    subTitleColor: Color,
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(30.dp))
            .width(layoutWidth)
            .height(layoutHeight)
            .background(color = Color.Magenta)
            .border(BorderStroke(2.dp, Red))
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_android_black_24dp),
            modifier = Modifier
                .width(100.dp)
                .height(100.dp)
                .fillMaxHeight(600f)
                .fillMaxWidth(800f)
//                .fillMaxWidth()
                .align(alignment)
                .padding(12.dp),
            contentDescription = "My content description",
        )

        Text(
            modifier = Modifier
                .align(titleAlignment),
            text = title,
            color = titleColor,
            fontSize = titleFontSize,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
        Text(
            modifier = Modifier
                .align(subTitleAlignment),
            text = "$subTitle >",
            color = subTitleColor,
            fontSize = subTitleFontSize,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
fun FlexibleGrid(
    layoutViewState: LayoutViewState,
    gridWidth: Int,
    paddingValues: PaddingValues,
    verticalArrangement: Arrangement.Vertical,
    horizontalArrangement: Arrangement.Horizontal,
) {
    val itemsCount = layoutViewState.items.size
    val columnsCount = layoutViewState.columnsCount
    var itemsGrid = itemsCount
    var itemsInRow = 0
    if (itemsCount > columnsCount) {
        itemsInRow = itemsCount % columnsCount
        if (itemsInRow != 0) {
            itemsGrid = itemsCount - itemsInRow
        }
    }

    val itemsHeight = layoutViewState.itemsHeight
    var itemHeight = 0
    when (itemsHeight) {
        is ItemsHeight.Fixed -> itemHeight = itemsHeight.height
        is ItemsHeight.Dynamic -> itemHeight = gridWidth * (itemsHeight.heightPercent) / 100
    }

    LazyVerticalGrid(
        columns = GridCells.Fixed(columnsCount),
        modifier = Modifier
            .width(gridWidth.dp)
            .background(layoutViewState.layoutBackground),
        contentPadding = paddingValues,
        verticalArrangement = verticalArrangement,
        horizontalArrangement = horizontalArrangement,
    ) {
        items(itemsGrid, span = { GridItemSpan(1) }) { index ->
            val item = layoutViewState.items.get(index)
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
                    val item = layoutViewState.items.get(layoutViewState.items.size - 1 - i)
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

@Composable
fun HorizontalScroll(
    layoutViewState: LayoutViewState,
    layoutWidth: Int,
    paddingValues: PaddingValues,
    horizontalArrangement: Arrangement.Horizontal,
) {
    val columnsCount = layoutViewState.columnsCount
    val parentPaddingStart = paddingValues.calculateStartPadding(LayoutDirection.Ltr).value
    val itemWidth =
        ((layoutWidth - parentPaddingStart - horizontalArrangement.spacing.value * columnsCount) / (columnsCount + 0.5)).toInt()

    val itemsHeight = layoutViewState.itemsHeight
    var itemHeight = 0
    when (itemsHeight) {
        is ItemsHeight.Fixed -> itemHeight = itemsHeight.height
        is ItemsHeight.Dynamic -> itemHeight = layoutWidth * (itemsHeight.heightPercent) / 100
    }

    LazyRow(
        modifier = Modifier
            .width(layoutWidth.dp)
            .background(layoutViewState.layoutBackground),
        contentPadding = paddingValues,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = horizontalArrangement,
    ) {
        items(layoutViewState.items) { item ->
            Box(
                modifier = Modifier
                    .width(itemWidth.dp)
                    .height(itemHeight.dp)
                    .background(color = item.background)
            ) {
                Text(text = item.title)
            }
        }
    }
}


