package com.linc.wordcard.data.network.api

import com.linc.wordcard.data.network.model.collection.CollectionApiModel
import com.linc.wordcard.data.network.model.word.UserWordApiModel
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

    suspend fun getUserCollections(
        id: String
    ): List<CollectionApiModel> {
        return get<List<CollectionApiModel>>(
            "/collections",
            mapOf("userId" to id)
        ).getOrThrow().orEmpty()
    }

    suspend fun getCollectionUserWords(
        collectionId: String,
        userId: String,
        page: Int,
        perPage: Int
    ): List<UserWordApiModel> {
        return get<List<UserWordApiModel>>(
            "/users/$userId/collections/$collectionId/words",
            mapOf(
                "perPage" to perPage.toString(),
                "page" to page.toString(),
            )
        ).getOrThrow().orEmpty()
    }

}