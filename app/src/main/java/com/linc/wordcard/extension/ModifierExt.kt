package com.linc.wordcard.extension

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp

fun Modifier.screenWidthPercent(percent: Float) =
    composed { width((LocalConfiguration.current.screenWidthDp * percent).dp) }

fun Modifier.screenHeightPercent(percent: Float) =
    composed { height((LocalConfiguration.current.screenHeightDp * percent).dp) }
