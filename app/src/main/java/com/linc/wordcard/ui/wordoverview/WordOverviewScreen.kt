package com.linc.wordcard.ui.wordoverview

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.linc.wordcard.R
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import com.linc.wordcard.extension.screenHeightPercent
import com.linc.wordcard.extension.throttleFirst
import com.linc.wordcard.ui.collectionoverview.model.*
import com.linc.wordcard.ui.theme.AppTheme
import com.linc.wordcard.ui.theme.WordUpTheme
import com.linc.wordcard.ui.wordoverview.model.*
import com.wajahatkarim.flippable.Flippable
import com.wajahatkarim.flippable.rememberFlipController

@Composable
fun WordOverviewScreen(
    state: WordOverviewUiState,
    onIntent: (WordOverviewIntent) -> Unit,
) {
    val flipController = rememberFlipController()
    if(state.currentWord == null) {
        return
    }
    ConstraintLayout(
        modifier = Modifier.fillMaxSize(),
    ) {
        val (card, controllers) = createRefs()
        Flippable(
            modifier = Modifier
                .height(IntrinsicSize.Min)
                .fillMaxWidth(0.8F)
                .constrainAs(card) {
                    linkTo(
                        start = parent.start,
                        end = parent.end,
                        top = parent.top,
                        bottom = controllers.top
                    )
                },
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
            flipController = flipController
        )
        Column(
            modifier = Modifier
                .fillMaxWidth(0.8F)
                .constrainAs(controllers) {
                    linkTo(start = parent.start, end = parent.end)
                    bottom.linkTo(parent.bottom)
                }
        ) {
            Spacer(modifier = Modifier.height(AppTheme.dimens.paddingHuge))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                ControlButton(icon = R.drawable.ic_bookmark_outlined) { onIntent(BookmarkClick) }
                ControlButton(icon = R.drawable.ic_check_circle_outlined) { onIntent(LearnClick) }
                ControlButton(icon = R.drawable.ic_edit) { onIntent(EditClick) }
            }
            Spacer(modifier = Modifier.height(AppTheme.dimens.paddingHuge))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                ControlButton(icon = R.drawable.ic_arrow_back) { onIntent(PreviousClick) }
                ControlButton(icon = R.drawable.ic_rotate_horizontal) { flipController.flip() }
                ControlButton(icon = R.drawable.ic_arrow_forward) { onIntent(NextClick) }
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
                .defaultMinSize(minHeight = screenHeightPercent(percent = 0.25F))
                .background(AppTheme.colors.primarySurfaceColor.copy(alpha = 0.5F)),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.height(AppTheme.dimens.paddingLarge))
            content.forEach { text ->
                Text(
                    text = text,
                    color = AppTheme.colors.primaryContentColor,
                    style = AppTheme.typographies.h4
                )
            }
            Spacer(modifier = Modifier.height(AppTheme.dimens.paddingLarge))
        }
    }
}


@Composable
fun ControlButton(
    modifier: Modifier = Modifier,
    @DrawableRes icon: Int,
    onClick: () -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    IconButton(
        modifier = Modifier.then(modifier),
        onClick = throttleFirst(coroutineScope = coroutineScope) { onClick() }
    ) {
        Icon(
            modifier = Modifier.size(AppTheme.dimens.iconExtraMedium),
            painter = painterResource(id = icon),
            contentDescription = null
        )
    }
}

@Preview(device = Devices.PIXEL_XL, showBackground = true)
@Composable
fun WordScreenPreview() {
    WordUpTheme {
        WordOverviewScreen(
            state = WordOverviewUiState(),
            onIntent = {}
        )
    }
}