package com.linc.wordcard.ui.newcollection.model

import android.net.Uri
import com.linc.wordcard.entity.Word

data class NewCollectionUiState(
    val name: String? = null,
    val document: Uri? = null,
    val words: List<DocWordUiState> = listOf(),
    val isProcessingDoc: Boolean = false
)

val NewCollectionUiState.isValidCollection: Boolean get() =
    !name.isNullOrEmpty() && document != null && words.isNotEmpty()