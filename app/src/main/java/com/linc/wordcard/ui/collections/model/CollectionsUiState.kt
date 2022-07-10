package com.linc.wordcard.ui.collections.model

import com.linc.wordcard.entity.WordsCollection

data class CollectionsUiState(
    val collections: List<WordsCollection> = listOf()
)