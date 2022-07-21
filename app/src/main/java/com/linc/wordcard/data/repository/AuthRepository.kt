package com.linc.wordcard.data.repository

import com.linc.wordcard.data.database.dao.UserDao
import com.linc.wordcard.data.mappers.toEntity
import com.linc.wordcard.data.network.api.AuthApiService
import com.linc.wordcard.data.network.model.user.UserApiModel
import com.linc.wordcard.data.preferences.AuthPreferences
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val authApiService: AuthApiService,
    private val userDao: UserDao,
    private val authPreferences: AuthPreferences,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    suspend fun signUp(
        name: String,
        login: String,
        password: String
    ) = withContext(ioDispatcher) {
        authApiService.postSignUp(name, login, password)?.let { saveUser(it) }
    }

    suspend fun signIn(
        login: String,
        password: String
    ) = withContext(ioDispatcher) {
        authApiService.postSignIn(login, password)?.let { saveUser(it) }
    }

    private suspend fun saveUser(userApiModel: UserApiModel) {
        userDao.insert(userApiModel.toEntity())
        authPreferences.putUserId(userApiModel.id)
    }
}