package com.linc.wordcard.ui.collections.model

import com.linc.wordcard.entity.Word
import com.linc.wordcard.entity.WordsCollection

data class SingleCollectionUiState(
    val id: String?,
    val name: String,
    val words: List<Word>,
    val onClick: () -> Unit
)

fun WordsCollection.toUiState(
    onClick: () -> Unit
) = SingleCollectionUiState(
    id = id,
    name = name,
    words = words,
    onClick = onClick
)