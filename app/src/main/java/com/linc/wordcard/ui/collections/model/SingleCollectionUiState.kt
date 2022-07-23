package com.linc.wordcard.ui.collections.model

import com.linc.wordcard.entity.collection.Collection

data class SingleCollectionUiState(
    val id: String?,
    val name: String,
    val wordsCount: Int,
    val onClick: () -> Unit
)

fun Collection.toUiState(
    onClick: () -> Unit
) = SingleCollectionUiState(
    id = id,
    name = name,
    wordsCount = wordsCount,
    onClick = onClick
)