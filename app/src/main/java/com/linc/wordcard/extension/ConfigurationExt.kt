package com.linc.wordcard.extension

import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun screenHeightPercent(percent: Float): Dp =
    (LocalConfiguration.current.screenHeightDp * percent).dp

@Composable
fun screenWidthPercent(percent: Float): Dp =
    (LocalConfiguration.current.screenWidthDp * percent).dp
