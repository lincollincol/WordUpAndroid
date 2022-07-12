package com.linc.wordcard.ui.collections

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.linc.wordcard.entity.WordsCollection
import com.linc.wordcard.ui.collections.model.CollectionsUiState
import com.linc.wordcard.ui.component.AppFloatingActionButton
import com.linc.wordcard.ui.navigation.model.AppScreen
import com.linc.wordcard.ui.theme.AppTheme
import com.linc.wordcard.ui.theme.WordUpTheme

@Composable
fun CollectionsScreen(
//    viewModel: CollectionsViewModel,
//    navController: NavController
    state: CollectionsUiState,
    onLoadCollections: () -> Unit,
    onCollectionClick: (id: String) -> Unit,
    onNewCollectionClick: () -> Unit,
) {
//    val state = viewModel.uiState

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            contentPadding = PaddingValues(AppTheme.dimens.paddingSmall),
            verticalArrangement = Arrangement.spacedBy(AppTheme.dimens.paddingMedium)
        ) {
            items(state.collections) { collection ->
                CollectionItem(collection = collection) {
                    onCollectionClick(requireNotNull(collection.id))
//                    navController.navigate(AppScreen.Card.createRoute())
                }
            }
        }
        AppFloatingActionButton(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(AppTheme.dimens.paddingMedium),
            imageVector = Icons.Default.Add,
            surfaceColor = AppTheme.colors.primarySurfaceColor,
            contentColor = AppTheme.colors.primaryContentColor,
            onClick = {
                onNewCollectionClick()
//                navController.navigate(AppScreen.NewCollection.name)
            }
        )
    }

    LaunchedEffect(key1 = Unit) {
        onLoadCollections()
//        viewModel.loadCollections()
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CollectionItem(
    modifier: Modifier = Modifier,
    collection: WordsCollection,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .then(modifier),
        color = AppTheme.colors.secondarySurfaceColor,
        shape = AppTheme.shapes.medium,
        elevation = AppTheme.dimens.elevationTiny,
        onClick = onClick
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
                text = "words: ${collection.words.count()}",
                style = AppTheme.typographies.caption,
                color = AppTheme.colors.secondaryContentColor
            )
        }
    }
}

@Preview
@Composable
fun CollectionItemPreview() {
    WordUpTheme {
        WordsCollection("", "Collection #1", listOf())
    }
}