/*
 * ToYou
 * Copyright © 2022 Aram Meem Company Limited. All Rights Reserved.
 */

package com.example.myapplication.ui.widgetItem

import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.TextUnit
import java.util.*

internal data class WidgetItemSubTitleViewState(
    val id: String = UUID.randomUUID().toString(),
    val subTitle: String,
    val alignment: Alignment,
    val fontSize: TextUnit,
    val color: Color,
)