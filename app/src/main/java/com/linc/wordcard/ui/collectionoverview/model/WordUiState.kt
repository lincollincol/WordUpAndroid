package com.linc.wordcard.ui.collectionoverview.model

import com.linc.wordcard.entity.word.UserWord
import com.linc.wordcard.extension.EMPTY

data class WordUiState(
    val id: String,
    val wordPreview: String = String.EMPTY,
    val translatePreview: String = String.EMPTY,
    val previewDivider: String = " — ",
    val learned: Boolean = false,
    val onItemClick: () -> Unit,
    val onMarkLearnedClick: () -> Unit,
)

fun UserWord.toUiState(
    onItemClick: () -> Unit,
    onMarkLearnedClick: () -> Unit
) = WordUiState(
    id = id,
    wordPreview = word,
    translatePreview = translate.first(),
    learned = learned,
    onItemClick = onItemClick,
    onMarkLearnedClick = onMarkLearnedClick
)