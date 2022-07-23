package com.linc.wordcard.ui.collections

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.linc.wordcard.entity.collection.Collection
import com.linc.wordcard.ui.collections.model.CollectionsIntent
import com.linc.wordcard.ui.collections.model.CollectionsUiState
import com.linc.wordcard.ui.collections.model.NewCollectionClick
import com.linc.wordcard.ui.collections.model.SingleCollectionUiState
import com.linc.wordcard.ui.component.AppFloatingActionButton
import com.linc.wordcard.ui.theme.AppTheme
import com.linc.wordcard.ui.theme.WordUpTheme

@Composable
fun CollectionsScreen(
    state: CollectionsUiState,
    onIntent: (CollectionsIntent) -> Unit
) {
//    val state = viewModel.uiState

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            contentPadding = PaddingValues(AppTheme.dimens.paddingSmall),
            verticalArrangement = Arrangement.spacedBy(AppTheme.dimens.paddingMedium)
        ) {
            items(state.collections) { collection ->
                CollectionItem(collection = collection)
            }
        }
        AppFloatingActionButton(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(AppTheme.dimens.paddingMedium),
            imageVector = Icons.Default.Add,
            surfaceColor = AppTheme.colors.primarySurfaceColor,
            contentColor = AppTheme.colors.primaryContentColor,
            onClick = { onIntent(NewCollectionClick) }
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CollectionItem(
    modifier: Modifier = Modifier,
    collection: SingleCollectionUiState
) {
    Surface(
        modifier = Modifier
            .then(modifier),
        color = AppTheme.colors.secondarySurfaceColor,
        shape = AppTheme.shapes.medium,
        elevation = AppTheme.dimens.elevationTiny,
        onClick = collection.onClick
    ) {
        Column(
            modifier = Modifier
                .padding(AppTheme.dimens.paddingSmall)
                .fillMaxWidth()
                .padding(AppTheme.dimens.paddingSmall)
        ) {
            Text(
                text = collection.name,
                style = AppTheme.typographies.h6,
                fontWeight = FontWeight.Bold,
                color = AppTheme.colors.secondaryContentColor
            )
            Spacer(modifier = Modifier.height(AppTheme.dimens.paddingSmall))
            Text(
                text = "words: ${collection.wordsCount}",
                style = AppTheme.typographies.caption,
                color = AppTheme.colors.secondaryContentColor
            )
        }
    }
}

@Composable
fun CollectionItemPreview() {
    WordUpTheme {
        Collection("", "Collection #1", 0)
    }
}

@Preview(device = Devices.PIXEL_XL, showBackground = true)
@Composable
fun CollectionPreview() {
    WordUpTheme {
        CollectionsScreen(
            state = CollectionsUiState(),
            onIntent = {}
        )
    }
}