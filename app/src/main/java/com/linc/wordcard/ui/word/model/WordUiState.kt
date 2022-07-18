package com.linc.wordcard.ui.word.model

import com.linc.wordcard.entity.Word
import com.linc.wordcard.extension.EMPTY

data class WordUiState(
    val original: String = String.EMPTY,
    val translateVariants: List<String> = listOf()
)