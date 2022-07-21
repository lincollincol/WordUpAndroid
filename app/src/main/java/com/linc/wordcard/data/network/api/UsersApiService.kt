package com.linc.wordcard.data.network.api

import com.linc.wordcard.data.network.model.user.UserApiModel
import io.ktor.client.*
import javax.inject.Inject

class UsersApiService @Inject constructor(
    httpClient: HttpClient
) : BaseApiService(httpClient) {



    suspend fun getUser(id: String): UserApiModel? {
        return get<UserApiModel>("/users/$id").getOrThrow()
    }

    suspend fun getUsers(): List<UserApiModel> {
        return get<List<UserApiModel>>("/users").getOrThrow().orEmpty()
    }

}