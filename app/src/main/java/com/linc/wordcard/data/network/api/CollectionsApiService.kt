package com.linc.wordcard.data.network.api

import com.linc.wordcard.data.network.model.collection.CollectionApiModel
import com.linc.wordcard.extension.binaryHeaders
import com.linc.wordcard.extension.toFormPart
import io.ktor.client.*
import io.ktor.client.request.forms.*
import io.ktor.http.*
import jodd.net.MimeTypes
import java.io.File
import javax.inject.Inject

class CollectionsApiService @Inject constructor(
    httpClient: HttpClient
) : BaseApiService(httpClient) {

    suspend fun postCollection(
        userId: String,
        name: String,
        collection: File
    ) {
        val parts = buildList<FormPart<*>> {
            add(FormPart("userId", userId))
            add(FormPart("name", name))
            add(collection.toFormPart("collection"))
        }
        multipart<Unit>("/collections", parts)
    }

    suspend fun getUserCollections(id: String): List<CollectionApiModel> {
        return getCollections(mapOf("userId" to id))
    }

    private suspend fun getCollections(
        parameters: Map<String, String>
    ): List<CollectionApiModel> {
        return get<List<CollectionApiModel>>("/collections", parameters)
            .getOrThrow()
            .orEmpty()
    }
}