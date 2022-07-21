package com.linc.wordcard.data.network.api

import com.linc.wordcard.data.network.model.auth.SignInApiModel
import com.linc.wordcard.data.network.model.auth.SignUpApiModel
import com.linc.wordcard.data.network.model.user.UserApiModel
import io.ktor.client.*
import javax.inject.Inject

class AuthApiService @Inject constructor(
    httpClient: HttpClient
) : BaseApiService(httpClient) {

    suspend fun postSignUp(name: String, login: String, password: String): UserApiModel? {
        return post<UserApiModel, SignUpApiModel>(
            path = "/sign-up",
            body = SignUpApiModel(name, password, login)
        ).getOrThrow()
    }

    suspend fun postSignIn(login: String, password: String): UserApiModel? {
        return post<UserApiModel, SignInApiModel>(
            path = "/sign-in",
            body = SignInApiModel(login, password)
        ).getOrThrow()
    }

}