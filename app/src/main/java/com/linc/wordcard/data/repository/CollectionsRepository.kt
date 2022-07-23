package com.linc.wordcard.data.repository

import android.net.Uri
import com.linc.wordcard.data.mappers.toModel
import com.linc.wordcard.data.network.api.CollectionsApiService
import com.linc.wordcard.data.network.model.collection.CollectionApiModel
import com.linc.wordcard.data.preferences.AuthPreferences
import com.linc.wordcard.data.utils.LocalFileManager
import com.linc.wordcard.data.utils.XSLSManager
import com.linc.wordcard.entity.collection.Collection
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CollectionsRepository @Inject constructor(
    private val collectionsApiService: CollectionsApiService,
    private val authPreferences: AuthPreferences,
    private val localFileManager: LocalFileManager,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    suspend fun saveCollection(
        name: String,
        uri: Uri,
    ) = withContext(ioDispatcher){
        val collectionFile = requireNotNull(localFileManager.getTempFile(uri))
        collectionsApiService.postCollection(
            userId = authPreferences.getUserId(),
            name = name,
            collection = collectionFile
        )
    }

    suspend fun loadUserCollections(): List<Collection> = withContext(ioDispatcher) {
        return@withContext collectionsApiService
            .getUserCollections(authPreferences.getUserId())
            .map(CollectionApiModel::toModel)
    }

}