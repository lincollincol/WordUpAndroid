package com.linc.wordcard.ui.theme

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Dimens(
    /** Padding */
    val paddingNone: Dp,
    val paddingTiny: Dp,
    val paddingSmall: Dp,
    val paddingMedium: Dp,
    val paddingLarge: Dp,
    val paddingHuge: Dp,

    /** Icons */
    val iconTiny: Dp,
    val iconSmall: Dp,
    val iconExtraMedium: Dp,
    val iconMedium: Dp,
    val iconLarge: Dp,
    val iconHuge: Dp,

    /** Elevation */
    val elevationTiny: Dp,
    val elevationSmall: Dp,
    val elevationMedium: Dp,
    val elevationLarge: Dp,
    val elevationHuge: Dp,

    /** View */
    val toolbar: Dp,

    val fabSmall: Dp,
    val fabMedium: Dp,
    val fabLarge: Dp,

)

val dimens = Dimens(
    paddingNone = 0.dp,
    paddingTiny = 2.dp,
    paddingSmall = 4.dp,
    paddingMedium = 8.dp,
    paddingLarge = 16.dp,
    paddingHuge = 32.dp,

    iconTiny = 12.dp,
    iconSmall = 24.dp,
    iconExtraMedium = 32.dp,
    iconMedium = 36.dp,
    iconLarge = 48.dp,
    iconHuge = 96.dp,

    elevationTiny = 2.dp,
    elevationSmall = 4.dp,
    elevationMedium = 6.dp,
    elevationLarge = 8.dp,
    elevationHuge = 10.dp,

    toolbar = 56.dp,

    fabSmall = 56.dp,
    fabMedium = 72.dp,
    fabLarge = 96.dp,
)