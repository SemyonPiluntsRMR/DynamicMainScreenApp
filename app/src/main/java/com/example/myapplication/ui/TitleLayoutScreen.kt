package com.example.myapplication.ui.adapters

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.graphics.Color.Companion.Magenta
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.TitleViewState

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    val titleViewState = TitleViewState(
        title = "MyTitle",
        layoutMarginTop = 10,
        layoutMarginBottom = 10,
        fontSize = 18,
        fontWeight = FontWeight(500),
        titleBackground = Green,
        textColor = Magenta,
    )
    TitleLayout(titleViewState,
        PaddingValues(start = 15.dp, end = 15.dp, top = 0.dp, bottom = 0.dp))
}

@Composable
fun TitleLayout(
    titleViewState: TitleViewState,
    paddingValues: PaddingValues,
) {
    with(titleViewState) {
        Text(
            text = title,
            modifier = Modifier
                .fillMaxWidth()
                .padding(PaddingValues(start = 0.dp, end = 0.dp, top = layoutMarginTop.dp, bottom = layoutMarginBottom.dp))
                .background(titleBackground)
                .padding(paddingValues),
            fontSize = fontSize.sp,
            fontWeight = fontWeight,
            color = textColor
        )
    }
}




