package com.linc.wordcard.ui.newcollection.model

import com.linc.wordcard.entity.Word
import com.linc.wordcard.ui.common.ItemUiState

data class DocWordUiState(
    val original: String,
    val translate: String,
    val divider: String,
) : ItemUiState {
    override val itemId: String get() = original
}

fun Word.toUiState() = DocWordUiState(
    original = original,
    translate = translateVariants.joinToString(", "),
    divider = " — "
)
