package com.linc.wordcard.data.repository

import com.linc.wordcard.data.network.api.UsersApiService
import javax.inject.Inject

class UsersRepository @Inject constructor(
    private val usersApiService: UsersApiService
) {

    suspend fun loadUsers() {
        usersApiService.getUsers().forEach {
            println(it.name)
        }
    }

}