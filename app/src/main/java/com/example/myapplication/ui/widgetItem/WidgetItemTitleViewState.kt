/*
 * ToYou
 * Copyright © 2022 Aram Meem Company Limited. All Rights Reserved.
 */

package com.example.myapplication.ui.widgetItem

import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.TextUnit
import java.util.*

internal data class WidgetItemTitleViewState(
    val id: String = UUID.randomUUID().toString(),
    val title: String,
    val alignment: Alignment,
    val fontSize: TextUnit,
    val color: Color,
)