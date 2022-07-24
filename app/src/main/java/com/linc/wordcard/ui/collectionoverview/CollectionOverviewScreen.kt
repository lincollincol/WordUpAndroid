package com.linc.wordcard.ui.collectionoverview

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Task
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.linc.wordcard.ui.collectionoverview.model.*
import com.linc.wordcard.ui.theme.AppTheme
import com.linc.wordcard.ui.theme.WordUpTheme
import com.wajahatkarim.flippable.Flippable
import com.wajahatkarim.flippable.rememberFlipController

@Composable
fun CollectionOverviewScreen(
    collectionId: String,
    state: CollectionOverviewUiState,
    onIntent: (WordIntent) -> Unit,
) {
    LaunchedEffect(collectionId) {
        onIntent(LoadCollection(collectionId))
    }
    state.currentWord ?: return
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Flippable(
            modifier = Modifier
                .fillMaxWidth(0.8F)
                .fillMaxHeight(0.25F),
            frontSide = {
                WordCard(
                    content = state.currentWord.word,
                    modifier = Modifier.fillMaxSize()
                )
            },
            backSide = {
                WordCard(
                    content = state.currentWord.translate,
                    modifier = Modifier.fillMaxSize()
                )
            },
            flipController = rememberFlipController()
        )

        Spacer(modifier = Modifier.height(AppTheme.dimens.paddingHuge))

        Row(
            modifier = Modifier.fillMaxWidth(0.8F),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            CardControlButton(icon = Icons.Filled.Bookmark) {
                onIntent(NextWordClick)
            }
            CardControlButton(icon = Icons.Default.Task) {

            }
            CardControlButton(icon = Icons.Filled.Bookmark) {

            }
        }
    }
}


@Composable
fun WordCard(
    modifier: Modifier = Modifier,
    content: List<String>
) {
    Card(
        modifier = Modifier.then(modifier),
        elevation = AppTheme.dimens.paddingMedium,
        shape = AppTheme.shapes.large
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(AppTheme.colors.primarySurfaceColor.copy(alpha = 0.5F)),
            verticalArrangement = Arrangement.Center,
        ) {
            content.forEach { text ->
                Text(
                    text = text,
                    color = AppTheme.colors.primaryContentColor,
                    style = AppTheme.typographies.h5
                )
            }
        }
    }
}


@Composable
fun CardControlButton(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    onClick: () -> Unit
) {
    IconButton(
        modifier = Modifier.then(modifier),
        onClick = onClick
    ) {
        Icon(
            modifier = Modifier.size(AppTheme.dimens.iconMedium),
            imageVector = icon,
            contentDescription = null
        )
    }
}

@Preview(device = Devices.PIXEL_XL, showBackground = true)
@Composable
fun WordScreenPreview() {
    WordUpTheme {
        CollectionOverviewScreen(
            collectionId = "",
            state = CollectionOverviewUiState(),
            onIntent = {}
        )
    }
}