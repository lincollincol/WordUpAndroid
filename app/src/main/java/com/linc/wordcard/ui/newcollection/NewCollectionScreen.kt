package com.linc.wordcard.ui.newcollection

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.linc.wordcard.ui.component.AppFloatingActionButton
import com.linc.wordcard.ui.component.AppTextField
import com.linc.wordcard.ui.newcollection.model.*
import com.linc.wordcard.ui.theme.AppTheme
import com.linc.wordcard.ui.theme.WordUpTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NewCollectionScreen(
    state: NewCollectionUiState,
    onIntent: (NewCollectionIntent) -> Unit
) {
    val docPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenDocument(),
        onResult = { onIntent(DocumentChange(it)) }
    )
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(AppTheme.dimens.paddingLarge)
        ) {
            AppTextField(
                modifier = Modifier.fillMaxWidth(),
                value = state.name.orEmpty(),
                hint = "Collection name",
                singleLine = true,
                startIcon = Icons.Default.Title,
                onValueChange = { onIntent(NameChange(it)) }
            )
            Spacer(modifier = Modifier.height(AppTheme.dimens.paddingLarge))
            AppTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    ) {
                        docPickerLauncher.launch(arrayOf("application/*"))
                    },
                value = "",
                hint = state.document?.path ?: "Source file",
                enabled = false,
                singleLine = true,
                startIcon = Icons.Default.FolderOpen,
                onValueChange = {}
            )
            Spacer(modifier = Modifier.height(AppTheme.dimens.paddingLarge))
            Box(modifier = Modifier.fillMaxSize()) {
                this@Column.AnimatedVisibility(
                    modifier = Modifier.align(Alignment.Center),
                    visible = state.isProcessingDoc
                ) {
                    CircularProgressIndicator(
                        color = AppTheme.colors.primarySurfaceColor
                    )
                }
                this@Column.AnimatedVisibility(
                    visible = !state.isProcessingDoc,
                    enter = fadeIn()
                ) {
                    LazyColumn {
                        items(state.words, key = { it.itemId }) {
                            DocumentWordItem(itemState = it)
                        }
                    }
                }
            }
        }
        AppFloatingActionButton(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(AppTheme.dimens.paddingMedium),
            enabled = state.isValidCollection,
            imageVector = Icons.Default.Check,
            surfaceColor = AppTheme.colors.primarySurfaceColor,
            contentColor = AppTheme.colors.primaryContentColor,
            onClick = {}
        )
    }
}


@Composable
fun DocumentWordItem(
    modifier: Modifier = Modifier,
    itemState: DocWordUiState,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier)
    ) {
        Text(
            text = itemState.original,
            style = AppTheme.typographies.h6,
            fontWeight = FontWeight.SemiBold
        )
        Text(
            text = itemState.divider,
            style = AppTheme.typographies.h6
        )
        Text(
            text = itemState.translate,
            style = AppTheme.typographies.h6.copy(fontWeight = FontWeight.Normal)
        )
    }
}

@Preview(device = Devices.PIXEL_XL, showBackground = true)
@Composable
private fun NewCollectionScreenPreview() {
    WordUpTheme {
        NewCollectionScreen(
            state = NewCollectionUiState(),
            onIntent = {}
        )
    }
}