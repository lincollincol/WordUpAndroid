package com.linc.wordcard.data.repository

import com.linc.wordcard.data.network.api.UsersApiService
import com.linc.wordcard.data.preferences.AuthPreferences
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UsersRepository @Inject constructor(
    private val usersApiService: UsersApiService,
    private val authPreferences: AuthPreferences,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    suspend fun createUser(
        name: String,
        login: String,
        password: String
    ) = withContext(ioDispatcher) {
        val user = usersApiService.postUser(name, login, password) ?: return@withContext
        authPreferences.putUserId(user.id)
    }

    suspend fun loadUsers() {
        usersApiService.getUsers().forEach {
            println(it.name)
        }
    }

}