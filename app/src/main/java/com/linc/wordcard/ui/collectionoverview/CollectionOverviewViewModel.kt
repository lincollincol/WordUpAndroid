package com.linc.wordcard.ui.collectionoverview

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.linc.wordcard.data.repository.WordsRepository
import com.linc.wordcard.ui.collectionoverview.model.*
import com.linc.wordcard.ui.common.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CollectionOverviewViewModel @Inject constructor(
    private val wordsRepository: WordsRepository
) : BaseViewModel<CollectionOverviewUiState, WordIntent>() {

    override var uiState: CollectionOverviewUiState by mutableStateOf(CollectionOverviewUiState())
        private set

    override fun obtainIntent(intent: WordIntent) {
        when(intent) {
            is BookmarkClick -> handleBookmark()
            is LearnClick -> handleLearn()
            is EditClick -> handleEdit()
            is NextClick -> handleNext()
            is PreviousClick -> handlePrevious()
        }
    }

    fun loadCollectionWords(id: String) = launchCoroutine {
        val words = wordsRepository.loadCollectionWords(id)
            .map { it.toUiState() }
        uiState = uiState.copy(currentWord = words.random())
    }

    private fun handleBookmark() = launchCoroutine {}

    private fun handleLearn() = launchCoroutine {}

    private fun handleEdit() = launchCoroutine {}

    private fun handlePrevious() = launchCoroutine {}

    private fun handleNext() = launchCoroutine {}

}