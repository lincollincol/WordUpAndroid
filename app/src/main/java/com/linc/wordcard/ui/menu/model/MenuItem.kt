package com.linc.wordcard.ui.menu.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmarks
import androidx.compose.material.icons.filled.Collections
import androidx.compose.ui.graphics.vector.ImageVector

sealed class MenuItem(
    val icon: ImageVector,
    val label: String
) {
    object Collections : MenuItem(Icons.Default.Collections, "Collections")
    object Bookmarks : MenuItem(Icons.Default.Bookmarks, "Bookmarks")
}