/*
 * ToYou
 * Copyright © 2022 Aram Meem Company Limited. All Rights Reserved.
 */

package com.example.myapplication.ui.widgetItem

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R

@Preview
@Composable
private fun WidgetLayoutPreview(
//    @PreviewParameter(WidgetItemViewStatesProvider::class) itemViewStates: List<WidgetLayoutItemViewState>,
) {
    val viewState = WidgetItemViewState(
        layoutWidth = 300.dp,
        layoutHeight = 200.dp,
        paddingValues = PaddingValues(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 16.dp),
        alignmentImg = Alignment.CenterEnd
    )
    val titleViewState = WidgetItemTitleViewState(
        title = "MyTitle",
        alignment = Alignment.TopStart,
        fontSize = 28.sp,
        color = Color.Yellow
    )

    val subTitleViewState = WidgetItemSubTitleViewState(
        subTitle = "MySubTitle",
        alignment = Alignment.BottomStart,
        fontSize = 18.sp,
        color = Color.Black
    )

    WidgetItemLayout(
        viewState = viewState,
        titleViewState = titleViewState,
        subTitleViewState = subTitleViewState,
        onClick = {})
}

@Composable
internal fun WidgetItemLayout(
    viewState: WidgetItemViewState,
    titleViewState: WidgetItemTitleViewState,
    subTitleViewState: WidgetItemSubTitleViewState,
    onClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(30.dp))
            .width(viewState.layoutWidth)
            .height(viewState.layoutHeight)
            .background(color = Color.Magenta)
            .border(BorderStroke(2.dp, Color.Red))
    ) {
        Image(
            painter = painterResource(R.drawable.ic_android_black_24dp),
            modifier = Modifier
                .width(100.dp)
                .height(100.dp)
//                .fillMaxWidth()
                .align(viewState.alignmentImg)
                .padding(12.dp),
            contentDescription = "My content description",
        )
        with(titleViewState) {
            Text(
                modifier = Modifier.align(titleViewState.alignment),
                text = title,
                color = color,
                fontSize = fontSize,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }

        with(subTitleViewState) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(alignment),
            ) {
                Text(
                    text = subTitle,
                    color = color,
                    fontSize = fontSize,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Image(
                    painter = painterResource(R.drawable.ic_android_black_24dp),
                    modifier = Modifier
                        .width(20.dp)
                        .height(20.dp)
                        .align(Alignment.CenterVertically)
                        .padding(start = 10.dp),
                    contentDescription = "My content description",
                )
            }
        }
    }
}