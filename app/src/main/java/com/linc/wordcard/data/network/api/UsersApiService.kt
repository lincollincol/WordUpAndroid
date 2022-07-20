package com.linc.wordcard.data.network.api

import com.linc.wordcard.data.network.model.BaseResponse
import com.linc.wordcard.data.network.model.NewUserApiModel
import com.linc.wordcard.data.network.model.UserApiModel
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import javax.inject.Inject

class UsersApiService @Inject constructor(
    private val client: HttpClient
) {

    suspend fun postUser(name: String, login: String, password: String): UserApiModel? {
        return try {
            val response = client.post {
                url.path("/users")
                setBody(NewUserApiModel(name, password, login))
            }.body<BaseResponse<UserApiModel>>()
            response.data
        } catch (e: Exception) {
            null
        }
    }

    suspend fun getUsers(): List<UserApiModel> {
        return try {
            val response = client.get {
                url.path("/users")
            }.body<BaseResponse<List<UserApiModel>>>()
            response.data.orEmpty()
        } catch (e: Exception) {
            emptyList()
        }
    }

}