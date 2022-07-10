package com.linc.wordcard.ui.component

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.linc.wordcard.ui.menu.model.MenuItem
import com.linc.wordcard.ui.theme.AppTheme
import com.linc.wordcard.ui.theme.WordCardTheme

@Composable
fun DrawerContent(
    modifier: Modifier = Modifier,
    selectedItem: MenuItem,
    items: List<MenuItem>,
    onItemClick: (MenuItem) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .background(AppTheme.colors.secondarySurfaceColor)
            .then(modifier),
        contentPadding = PaddingValues(horizontal = AppTheme.dimens.paddingSmall),
        verticalArrangement = Arrangement.spacedBy(AppTheme.dimens.paddingSmall)
    ) {
        items(items) { menuItem ->
            DrawerItem(
                icon = menuItem.icon,
                label = menuItem.label,
                isSelected = menuItem == selectedItem,
                onClick = { onItemClick(menuItem) }
            )
        }
    }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DrawerItem(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    label: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val backgroundColor by animateColorAsState(
        targetValue = when {
            isSelected -> AppTheme.colors.primarySurfaceColor.copy(alpha = 0.7F)
            else -> Color.Transparent
        }
    )
    val contentColor = when {
        isSelected -> AppTheme.colors.primaryContentColor
        else -> AppTheme.colors.secondaryContentColor
    }
    Surface(
        modifier = Modifier
            .height(56.dp)
            .then(modifier),
        shape = AppTheme.shapes.medium,
        color = backgroundColor,
        onClick = onClick,
        contentColor = contentColor
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = AppTheme.dimens.paddingSmall),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(imageVector = icon, contentDescription = null,)
            Spacer(modifier = Modifier.width(AppTheme.dimens.paddingMedium))
            Text(text = label, color = contentColor)
        }
    }
}

@Preview
@Composable
fun DrawerItemPreview() {
    WordCardTheme {
        DrawerItem(icon = Icons.Default.Home, label = "Text", isSelected = true, onClick = {})
    }
}

@Preview
@Composable
fun DrawerContentPreview() {
    WordCardTheme {
        DrawerContent(
            selectedItem = MenuItem.Collections,
            items = listOf(MenuItem.Collections, MenuItem.Bookmarks),
            onItemClick = {}
        )
    }
}