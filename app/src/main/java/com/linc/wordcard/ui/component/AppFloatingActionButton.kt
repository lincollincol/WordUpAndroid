package com.linc.wordcard.ui.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.linc.wordcard.ui.theme.AppTheme
import com.linc.wordcard.ui.theme.WordCardTheme

@Composable
fun AppFloatingActionButton(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    imageVector: ImageVector,
    surfaceColor: Color,
    contentColor: Color,
    onClick: () -> Unit
) {
    Button(
        modifier = Modifier
            .size(AppTheme.dimens.fabMedium)
            .then(modifier),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = surfaceColor,
            contentColor = contentColor
        ),
        elevation = ButtonDefaults.elevation(AppTheme.dimens.elevationSmall),
        shape = AppTheme.shapes.large,
        contentPadding = PaddingValues(),
        enabled = enabled,
        onClick = onClick
    ) {
        Icon(imageVector = imageVector, contentDescription = null)
    }
}

@Preview
@Composable
private fun AppFloatingActionButtonPreview() {
    WordCardTheme {
        AppFloatingActionButton(
            imageVector = Icons.Default.Check,
            surfaceColor = AppTheme.colors.primarySurfaceColor,
            contentColor = AppTheme.colors.primaryContentColor,
            onClick = {}
        )
    }
}