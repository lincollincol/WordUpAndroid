package com.linc.wordcard.ui.wordoverview

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.linc.wordcard.data.repository.WordsRepository
import com.linc.wordcard.extension.next
import com.linc.wordcard.extension.previous
import com.linc.wordcard.ui.collectionoverview.model.*
import com.linc.wordcard.ui.common.BaseViewModel
import com.linc.wordcard.ui.wordoverview.model.*
import com.linc.wordcard.ui.wordoverview.model.WordUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WordOverviewViewModel @Inject constructor(
    private val wordsRepository: WordsRepository
) : BaseViewModel<WordOverviewUiState, WordOverviewIntent>() {

    override var uiState: WordOverviewUiState by mutableStateOf(WordOverviewUiState())
        private set

    private val words: MutableList<WordUiState> = mutableListOf()

    override fun obtainIntent(intent: WordOverviewIntent) {
        when(intent) {
            is BookmarkClick -> handleBookmark()
            is LearnClick -> handleLearn()
            is EditClick -> handleEdit()
            is NextClick -> handleNext()
            is PreviousClick -> handlePrevious()
        }
    }

    fun loadCollectionWords(id: String) = launchCoroutine {
        wordsRepository.loadCollectionWords(id)
            .sortedBy { it.collectionIndex }
            .map {
                it.toUiState()
            }
            .let(words::addAll)
        uiState = uiState.copy(currentWord = words.random())
    }

    private fun handleBookmark() = launchCoroutine {}

    private fun handleLearn() = launchCoroutine {}

    private fun handleEdit() = launchCoroutine {}

    private fun handlePrevious() = launchCoroutine {
        val previousWord = uiState.currentWord?.let(words::previous)
        uiState = uiState.copy(currentWord = previousWord)
    }

    private fun handleNext() = launchCoroutine {
        val nextWord = uiState.currentWord?.let(words::next)
        uiState = uiState.copy(currentWord = nextWord)
    }

}