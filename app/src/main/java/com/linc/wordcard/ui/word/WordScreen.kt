package com.linc.wordcard.ui.word

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.linc.wordcard.ui.theme.AppTheme
import com.linc.wordcard.ui.theme.WordUpTheme
import com.linc.wordcard.ui.word.model.LoadWord
import com.linc.wordcard.ui.word.model.WordIntent
import com.linc.wordcard.ui.word.model.WordUiState
import com.wajahatkarim.flippable.Flippable
import com.wajahatkarim.flippable.rememberFlipController

@Composable
fun WordScreen(
    wordId: String,
    state: WordUiState,
    onIntent: (WordIntent) -> Unit,
) {
    LaunchedEffect(wordId) {
        onIntent(LoadWord(wordId))
    }
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
                    word = "Word",
                    modifier = Modifier.fillMaxSize()
                )
            },
            backSide = {
                WordCard(
                    word = "Translate",
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

            }
            CardControlButton(icon = Icons.Filled.Bookmark) {

            }
            CardControlButton(icon = Icons.Filled.Bookmark) {

            }
        }
    }
}


@Composable
fun WordCard(
    modifier: Modifier = Modifier,
    word: String
) {
    Card(
        modifier = Modifier.then(modifier),
        elevation = AppTheme.dimens.paddingMedium,
        shape = AppTheme.shapes.large
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(AppTheme.colors.primarySurfaceColor.copy(alpha = 0.5F)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = word,
                color = AppTheme.colors.primaryContentColor,
                style = AppTheme.typographies.h3
            )
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
        WordCard(word = "Word")
    }
}