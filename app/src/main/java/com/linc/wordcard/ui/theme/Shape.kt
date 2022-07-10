package com.linc.wordcard.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp


data class Shapes(
    val none: Shape,
    val tiny: Shape,
    val small: Shape,
    val medium: Shape,
    val large: Shape,
    val huge: Shape,
)

val shapes = Shapes(
    none = RoundedCornerShape(0.dp),
    tiny = RoundedCornerShape(2.dp),
    small = RoundedCornerShape(4.dp),
    medium = RoundedCornerShape(8.dp),
    large = RoundedCornerShape(16.dp),
    huge = RoundedCornerShape(32.dp),
)