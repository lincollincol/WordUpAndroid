package com.linc.wordcard.data.network.api

import com.linc.wordcard.data.network.model.BaseResponse
import com.linc.wordcard.data.network.model.UserApiModel
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import javax.inject.Inject

class UsersApiService @Inject constructor(
    private val client: HttpClient
) {

    suspend fun postUser() {
        client.post {
            url.path("/users")
        }
    }

    suspend fun getUsers(): List<UserApiModel> {
        return try {
            client.get {
                url.path("/users")
            }.body<BaseResponse<List<UserApiModel>>>().data.orEmpty()
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }

}