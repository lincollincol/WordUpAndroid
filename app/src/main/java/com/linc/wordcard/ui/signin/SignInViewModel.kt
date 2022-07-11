package com.linc.wordcard.ui.signin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.linc.wordcard.data.repository.UsersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val usersRepository: UsersRepository
) : ViewModel() {

    fun loadUsers() {
        viewModelScope.launch {
            usersRepository.loadUsers()
        }
    }

}