package com.linc.wordcard.ui.newcollection

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.linc.wordcard.data.repository.CollectionsRepository
import com.linc.wordcard.data.repository.DocumentRepository
import com.linc.wordcard.entity.word.Word
import com.linc.wordcard.ui.common.BaseViewModel
import com.linc.wordcard.ui.newcollection.model.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class NewCollectionViewModel @Inject constructor(
    private val documentRepository: DocumentRepository,
    private val collectionsRepository: CollectionsRepository,
) : BaseViewModel<NewCollectionUiState, NewCollectionIntent>() {

    override var uiState: NewCollectionUiState by mutableStateOf(NewCollectionUiState())
        private set

    override fun obtainIntent(intent: NewCollectionIntent) {
        when(intent) {
            is NameChange -> updateName(intent.value)
            is DocumentChange -> updateDocument(intent.value)
            is SaveClick -> handleSave()
        }
    }

    private fun updateName(name: String) {
        uiState = uiState.copy(name = name)
    }

    private fun updateDocument(uri: Uri?) {
        uri ?: return
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

    private fun handleSave() {
        viewModelScope.launch {
            try {
                if(!uiState.isValidCollection) {
                    return@launch
                }
                collectionsRepository.saveCollection(
                    name = uiState.name,
                    uri = uiState.document!!
                )
                navigateBack()
            } catch (e: Exception) {
                Timber.e(e)
            }
        }
    }
}