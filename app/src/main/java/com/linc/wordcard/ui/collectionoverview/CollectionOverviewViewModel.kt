package com.linc.wordcard.ui.collectionoverview

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.linc.wordcard.data.repository.CollectionsRepository
import com.linc.wordcard.data.repository.WordsRepository
import com.linc.wordcard.ui.collectionoverview.model.*
import com.linc.wordcard.ui.common.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class CollectionOverviewViewModel @Inject constructor(
    private val wordsRepository: WordsRepository
) : BaseViewModel<CollectionOverviewUiState, WordIntent>() {

    override var uiState: CollectionOverviewUiState by mutableStateOf(CollectionOverviewUiState())
        private set

    override fun obtainIntent(intent: WordIntent) {
        when(intent) {
            is LoadCollection -> loadCollectionWords(intent.collectionId)
            is BookmarkClick -> handleBookmark()
            is LearnClick -> handleLearn()
            is EditWordClick -> handleEdit()
            is NextWordClick -> handleNext()
            is PreviousWordClick -> handlePrevious()
        }
    }

    private fun loadCollectionWords(id: String) {
        viewModelScope.launch {
            try {
                val words = wordsRepository.loadCollectionWords(id)
                    .map { it.toUiState() }
                uiState = uiState.copy(currentWord = words.random())
            } catch (e: Exception) {
                Timber.e(e)
            }
        }
    }

    private fun handleBookmark() {
        viewModelScope.launch {
            try {

            } catch (e: Exception) {
                Timber.e(e)
            }
        }
    }

    private fun handleLearn() {
        viewModelScope.launch {
            try {

            } catch (e: Exception) {
                Timber.e(e)
            }
        }
    }

    private fun handleEdit() {
        viewModelScope.launch {
            try {

            } catch (e: Exception) {
                Timber.e(e)
            }
        }
    }

    private fun handlePrevious() {
        viewModelScope.launch {
            try {

            } catch (e: Exception) {
                Timber.e(e)
            }
        }
    }

    private fun handleNext() {
        viewModelScope.launch {
            try {

            } catch (e: Exception) {
                Timber.e(e)
            }
        }
    }

}