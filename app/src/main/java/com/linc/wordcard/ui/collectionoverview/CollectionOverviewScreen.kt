package com.linc.wordcard.ui.collectionoverview

import androidx.compose.animation.core.animateFloatAsState
import com.linc.wordcard.R
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.linc.wordcard.ui.collectionoverview.model.CollectionOverviewIntent
import com.linc.wordcard.ui.collectionoverview.model.CollectionOverviewUiState
import com.linc.wordcard.ui.collectionoverview.model.WordUiState
import com.linc.wordcard.ui.collections.model.SingleCollectionUiState
import com.linc.wordcard.ui.theme.AppTheme
import com.linc.wordcard.ui.theme.WordUpTheme

@Composable
fun CollectionOverviewScreen(
    state: CollectionOverviewUiState,
    onIntent: (CollectionOverviewIntent) -> Unit,
) {
    Surface(modifier = Modifier.fillMaxSize()) {
        LazyColumn {
            items(state.words) {
                WordItem(state = it)
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun WordItem(
    modifier: Modifier = Modifier,
    state: WordUiState,
) {
    val learnedIcon = painterResource(
        id = when {
            state.learned -> R.drawable.ic_check_circle_filled
            else -> R.drawable.ic_check_circle_outlined
        }
    )
    val learnedIconAlpha by animateFloatAsState(targetValue = if (state.learned) 1f else 0.5f )
    Surface(
        modifier = Modifier.then(modifier),
        color = AppTheme.colors.secondarySurfaceColor,
        shape = AppTheme.shapes.medium,
        elevation = AppTheme.dimens.elevationTiny,
        onClick = state.onItemClick
    ) {
        Row(
            modifier = Modifier
                .padding(AppTheme.dimens.paddingSmall)
                .fillMaxWidth()
                .padding(AppTheme.dimens.paddingSmall),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier.width(IntrinsicSize.Max)
            ) {
                Text(
                    text = state.wordPreview,
                    style = AppTheme.typographies.h6,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = state.previewDivider,
                    style = AppTheme.typographies.h6
                )
                Text(
                    text = state.translatePreview,
                    style = AppTheme.typographies.h6,
                    fontWeight = FontWeight.Normal
                )
            }
            IconButton(onClick = state.onMarkLearnedClick) {
                Icon(
                    modifier = Modifier.alpha(learnedIconAlpha),
                    painter = learnedIcon,
                    contentDescription = null
                )
            }
        }
    }
}

@Preview(device = Devices.PIXEL_XL, showBackground = true)
@Composable
private fun WordItemPreview() {
    WordUpTheme {
        WordItem(
            state = WordUiState(
                id = "",
                wordPreview = "Hello",
                translatePreview = "World",
                learned = false,
                onItemClick = {},
                onMarkLearnedClick = {}
            )
        )
    }
}

@Preview(device = Devices.PIXEL_XL, showBackground = true)
@Composable
private fun CollectionOverviewScreenPreview() {
    WordUpTheme {
        CollectionOverviewScreen(
            state = CollectionOverviewUiState(),
            onIntent = {}
        )
    }
}