package com.linc.wordcard.data.repository

import com.linc.wordcard.data.mappers.toModel
import com.linc.wordcard.data.network.api.CollectionsApiService
import com.linc.wordcard.data.network.model.word.UserWordApiModel
import com.linc.wordcard.data.preferences.AuthPreferences
import com.linc.wordcard.entity.word.UserWord
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class WordsRepository @Inject constructor(
    private val collectionsApiService: CollectionsApiService,
    private val authPreferences: AuthPreferences,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    suspend fun loadCollectionWords(
        collectionId: String
    ): List<UserWord> = withContext(ioDispatcher) {
        return@withContext collectionsApiService.getCollectionUserWords(
            collectionId = collectionId,
            userId = authPreferences.getUserId(),
            page = 0,
            perPage = 0
        ).map(UserWordApiModel::toModel)
    }

}