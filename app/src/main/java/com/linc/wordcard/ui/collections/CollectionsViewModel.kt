package com.linc.wordcard.ui.collections

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.linc.wordcard.entity.WordsCollection
import com.linc.wordcard.ui.collections.model.CollectionsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CollectionsViewModel @Inject constructor() : ViewModel() {

    var uiState: CollectionsUiState by mutableStateOf(CollectionsUiState())
        private set

    fun loadCollections() {
        val collections = List(20) {
            WordsCollection("$it", "Collection #$it", listOf())
        }
        uiState = uiState.copy(collections = collections)
    }

}