package com.linc.wordcard.ui.component

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import com.linc.wordcard.ui.theme.AppTheme
import com.linc.wordcard.ui.theme.WordCardTheme

@Composable
fun DrawerToolbar(
    modifier: Modifier = Modifier,
    title: String,
    elevation: Dp,
    onNavigationClick: () -> Unit
) {
    TopAppBar(
        modifier = Modifier.then(modifier),
        title = { Text(text = title) },
        navigationIcon = {
            IconButton(onClick = onNavigationClick) {
                Icon(imageVector = Icons.Default.Menu, contentDescription = null)
            }
        },
        elevation = elevation,
        backgroundColor = AppTheme.colors.secondarySurfaceColor,
        contentColor = AppTheme.colors.secondaryContentColor
    )
}

@Preview
@Composable
fun DrawerToolbarPreview() {
    WordCardTheme {
        DrawerToolbar(
            title = "Title",
            elevation = AppTheme.dimens.paddingMedium,
            onNavigationClick = {}
        )
    }
}