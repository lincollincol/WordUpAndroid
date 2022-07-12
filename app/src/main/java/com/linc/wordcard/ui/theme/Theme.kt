package com.linc.wordcard.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun WordUpTheme(content: @Composable () -> Unit) {
    CompositionLocalProvider(
        LocalColorProvider provides lightColorPalette,
        LocalShapeProvider provides shapes,
        LocalDimenProvider provides dimens,
//        LocalTypographyProvider provides typographies,
        LocalTypographyProvider provides typographies,
        content = content
    )
}

object AppTheme {
    val colors: Colors
        @Composable
        @ReadOnlyComposable
        get() = LocalColorProvider.current

    val shapes: Shapes
        @Composable
        @ReadOnlyComposable
        get() = LocalShapeProvider.current

    val dimens: Dimens
        @Composable
        @ReadOnlyComposable
        get() = LocalDimenProvider.current

//    val typographies: Typographies
    val typographies: Typography
        @Composable
        @ReadOnlyComposable
        get() = LocalTypographyProvider.current
}

val LocalColorProvider = staticCompositionLocalOf<Colors> {
    error("No default colors provided")
}

val LocalShapeProvider = staticCompositionLocalOf<Shapes> {
    error("No default shapes provided")
}

val LocalDimenProvider = staticCompositionLocalOf<Dimens> {
    error("No default typographies provided")
}

//val LocalTypographyProvider = staticCompositionLocalOf<Typographies> {
val LocalTypographyProvider = staticCompositionLocalOf<Typography> {
    error("No default dimens provided")
}