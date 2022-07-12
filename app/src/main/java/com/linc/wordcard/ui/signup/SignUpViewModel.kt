package com.linc.wordcard.ui.signup

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.linc.wordcard.data.repository.UsersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val usersRepository: UsersRepository
) : ViewModel() {

    var uiState: SignUpUiState by mutableStateOf(SignUpUiState())

    fun updateLogin(login: String) {
        uiState = uiState.copy(login = login)
    }

    fun updatePassword(password: String) {
        uiState = uiState.copy(password = password)
    }

    fun updateName(password: String) {
        uiState = uiState.copy(password = password)
    }

    fun loadUsers() {
        viewModelScope.launch {
            usersRepository.loadUsers()
        }
    }

}