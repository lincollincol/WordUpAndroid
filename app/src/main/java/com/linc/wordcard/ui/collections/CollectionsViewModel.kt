package com.linc.wordcard.ui.collections

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.linc.wordcard.data.repository.CollectionsRepository
import com.linc.wordcard.entity.collection.Collection
import com.linc.wordcard.ui.collections.model.CollectionsIntent
import com.linc.wordcard.ui.collections.model.CollectionsUiState
import com.linc.wordcard.ui.collections.model.NewCollectionClick
import com.linc.wordcard.ui.collections.model.toUiState
import com.linc.wordcard.ui.common.BaseViewModel
import com.linc.wordcard.ui.navigation.model.AppScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class CollectionsViewModel @Inject constructor(
    private val collectionsRepository: CollectionsRepository
) : BaseViewModel<CollectionsUiState, CollectionsIntent>() {

    override var uiState: CollectionsUiState by mutableStateOf(CollectionsUiState())
        private set

    init {
        loadCollections()
    }

    override fun obtainIntent(intent: CollectionsIntent) {
        when(intent) {
            NewCollectionClick -> handleNewCollection()
        }
    }

    private fun loadCollections() {
        viewModelScope.launch {
            try {
                val collections = collectionsRepository.loadUserCollections()
                    .map { it.toUiState { selectCollection(it) } }
                uiState = uiState.copy(collections = collections)
            } catch (e: Exception) {
                Timber.e(e)
            }
        }
    }

    private fun handleNewCollection() {
        navigateTo(AppScreen.NewCollection.route)
    }

    private fun selectCollection(collection: Collection) {
        val id = collection.id ?: return
        navigateTo(AppScreen.Card.createRoute(id))
    }

}