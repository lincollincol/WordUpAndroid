package com.linc.wordcard.ui.newcollection

import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.TwoWayConverter
import androidx.compose.animation.core.animateValueAsState
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.linc.wordcard.entity.Word
import com.linc.wordcard.ui.component.AppFloatingActionButton
import com.linc.wordcard.ui.component.AppTextField
import com.linc.wordcard.ui.navigation.model.AppScreen
import com.linc.wordcard.ui.newcollection.model.DocWordUiState
import com.linc.wordcard.ui.newcollection.model.isValidCollection
import com.linc.wordcard.ui.theme.AppTheme
import com.linc.wordcard.ui.theme.WordCardTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NewCollectionScreen(
    viewModel: NewCollectionViewModel,
    navController: NavController
) {
    val state = viewModel.uiState
    val docPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenDocument(),
        onResult = {
            it?.let(viewModel::updateDocument)
        }
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
                onValueChange = viewModel::updateName
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
                endIcon = Icons.Default.FolderOpen,
                onValueChange = {}
            )
            Spacer(modifier = Modifier.height(AppTheme.dimens.paddingLarge))
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                this@Column.AnimatedVisibility(visible = state.isProcessingDoc) {
                    CircularProgressIndicator(color = AppTheme.colors.primarySurfaceColor)
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
            style = AppTheme.typographies.large,
            fontWeight = FontWeight.SemiBold
        )
        Text(
            text = itemState.divider,
            style = AppTheme.typographies.large
        )
        Text(
            text = itemState.translate,
            style = AppTheme.typographies.large
        )
    }
}