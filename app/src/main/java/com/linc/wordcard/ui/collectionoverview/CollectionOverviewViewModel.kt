package com.linc.wordcard.ui.collectionoverview

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.linc.wordcard.data.repository.WordsRepository
import com.linc.wordcard.entity.word.UserWord
import com.linc.wordcard.ui.collectionoverview.model.CollectionOverviewIntent
import com.linc.wordcard.ui.collectionoverview.model.CollectionOverviewUiState
import com.linc.wordcard.ui.collectionoverview.model.WordUiState
import com.linc.wordcard.ui.collectionoverview.model.toUiState
import com.linc.wordcard.ui.common.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CollectionOverviewViewModel @Inject constructor(
    private val wordsRepository: WordsRepository
) : BaseViewModel<CollectionOverviewUiState, CollectionOverviewIntent>() {

    override var uiState: CollectionOverviewUiState by mutableStateOf(CollectionOverviewUiState())
        private set

    private val words: MutableList<WordUiState> = mutableListOf()

    override fun obtainIntent(intent: CollectionOverviewIntent) {
        when(intent) {
            else -> {}
        }
    }

    fun loadCollectionWords(id: String) = launchCoroutine {
        wordsRepository.loadCollectionWords(id)
            .sortedBy { it.collectionIndex }
            .map {
                it.toUiState(
                    onItemClick = { selectWord(it) },
                    onMarkLearnedClick = { updateWordLearnedState(it) }
                )
            }
            .let(words::addAll)
        uiState = uiState.copy(words = words)
    }

    private fun selectWord(word: UserWord) {

    }

    private fun updateWordLearnedState(word: UserWord) {

    }

}