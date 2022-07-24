package com.linc.wordcard.ui.newcollection.model

import android.net.Uri
import com.linc.wordcard.extension.EMPTY

data class NewCollectionUiState(
    val name: String = String.EMPTY,
    val document: Uri? = null,
    val words: List<DocWordUiState> = listOf(),
    val isProcessingDoc: Boolean = false
)

val NewCollectionUiState.isValidCollection: Boolean get() =
    name.isNotEmpty() && document != null && words.isNotEmpty()