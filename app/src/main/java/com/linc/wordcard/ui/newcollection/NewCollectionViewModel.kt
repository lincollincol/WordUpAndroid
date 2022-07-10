package com.linc.wordcard.ui.newcollection

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.linc.wordcard.data.repository.DocumentRepository
import com.linc.wordcard.entity.Word
import com.linc.wordcard.ui.newcollection.model.DocWordUiState
import com.linc.wordcard.ui.newcollection.model.NewCollectionUiState
import com.linc.wordcard.ui.newcollection.model.toUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewCollectionViewModel @Inject constructor(
    private val documentRepository: DocumentRepository
) : ViewModel() {

    var uiState: NewCollectionUiState by mutableStateOf(NewCollectionUiState())

    fun updateName(name: String) {
        uiState = uiState.copy(name = name)
    }

    fun updateDocument(uri: Uri) {
        viewModelScope.launch {
            try {
                uiState = uiState.copy(
                    document = uri,
                    isProcessingDoc = true
                )
                val words = documentRepository.loadDocumentWords(uri)
                    .map(Word::toUiState)
                uiState = uiState.copy(
                    document = uri,
                    words = words
                )
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                uiState = uiState.copy(isProcessingDoc = false)
            }
        }
    }

}