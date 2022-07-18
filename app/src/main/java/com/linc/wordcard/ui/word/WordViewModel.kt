package com.linc.wordcard.ui.word

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.linc.wordcard.ui.common.BaseViewModel
import com.linc.wordcard.ui.word.model.LoadWord
import com.linc.wordcard.ui.word.model.WordIntent
import com.linc.wordcard.ui.word.model.WordUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WordViewModel @Inject constructor() : BaseViewModel<WordUiState, WordIntent>() {

    override val uiState: WordUiState by mutableStateOf(WordUiState())

    override fun obtainIntent(intent: WordIntent) {
        when(intent) {
            is LoadWord -> loadWord(intent.id)
        }
    }

    private fun loadWord(id: String) {

    }

}