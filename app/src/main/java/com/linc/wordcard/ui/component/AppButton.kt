package com.linc.wordcard.ui.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.linc.wordcard.ui.theme.AppTheme
import com.linc.wordcard.ui.theme.WordUpTheme

@Composable
fun AppButton(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    surfaceColor: Color = AppTheme.colors.primarySurfaceColor,
    contentColor: Color = AppTheme.colors.primaryContentColor,
    elevation: Dp = AppTheme.dimens.elevationSmall,
    text: String,
    onClick: () -> Unit
) {
    Button(
        modifier = Modifier
            .height(56.dp)
            .then(modifier),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = surfaceColor,
            contentColor = contentColor
        ),
        elevation = ButtonDefaults.elevation(elevation),
        shape = AppTheme.shapes.large,
        contentPadding = PaddingValues(),
        enabled = enabled,
        onClick = onClick
    ) {
        Text(text = text, style = AppTheme.typographies.button)
    }
}

@Preview
@Composable
fun AppButtonPreview() {
    WordUpTheme {
        AppButton(text = "Button", onClick = {})
    }
}