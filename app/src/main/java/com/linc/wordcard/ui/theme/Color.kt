package com.linc.wordcard.ui.theme

import androidx.compose.ui.graphics.Color


data class Colors(
    val primaryContentColor: Color,
    val secondaryContentColor: Color,
    val primarySurfaceColor: Color,
    val secondarySurfaceColor: Color,
    val backgroundColor: Color,
    val errorColor: Color
)

val lightColorPalette = Colors(
    primaryContentColor = Color.White,
//    secondaryContentColor = Color(0xFF00BCD4),
    secondaryContentColor = Color.Black,
    primarySurfaceColor = Color(0xFF00BCD4),
    secondarySurfaceColor = Color.White,
    backgroundColor = Color.White,
    errorColor = Color(0xFFFF5722)
)