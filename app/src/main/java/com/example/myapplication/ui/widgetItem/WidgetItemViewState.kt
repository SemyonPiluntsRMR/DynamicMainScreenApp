/*
 * ToYou
 * Copyright © 2022 Aram Meem Company Limited. All Rights Reserved.
 */

package com.example.myapplication.ui.widgetItem

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.Dp
import java.util.*

internal data class WidgetItemViewState(
    val id: String = UUID.randomUUID().toString(),
    val layoutWidth: Dp,
    val layoutHeight: Dp,
    val paddingValues: PaddingValues,
    val alignmentImg: Alignment,
)
