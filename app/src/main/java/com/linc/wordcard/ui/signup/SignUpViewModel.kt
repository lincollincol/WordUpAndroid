package com.linc.wordcard.ui.signup

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.linc.wordcard.data.repository.UsersRepository
import com.linc.wordcard.ui.common.BaseViewModel
import com.linc.wordcard.ui.navigation.model.AppScreen
import com.linc.wordcard.ui.signup.model.*
import com.linc.wordcard.ui.signup.model.SignUpUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val usersRepository: UsersRepository
) : BaseViewModel<SignUpIntent>() {

    var uiState: SignUpUiState by mutableStateOf(SignUpUiState())

    override fun obtainIntent(intent: SignUpIntent) {
        when(intent) {
            is NameChange -> updateName(intent.value)
            is LoginChange -> updateLogin(intent.value)
            is PasswordChange -> updatePassword(intent.value)
            is SignUpClick -> handleSignUp()
            is SignInClick -> handleSignIn()
            is FinishNavigation -> handleFinishNavigation()
        }
    }

    private fun updateLogin(login: String) {
        uiState = uiState.copy(login = login)
    }

    private fun updatePassword(password: String) {
        uiState = uiState.copy(password = password)
    }

    private fun updateName(name: String) {
        uiState = uiState.copy(name = name)
    }

    private fun handleSignUp() {
//        uiState = uiState.copy(navRoute = AppScreen.SignUp.route)
    }

    private fun handleSignIn() {
        uiState = uiState.copy(navRoute = AppScreen.SignIn.route)
    }

    private fun handleFinishNavigation() {
        uiState = uiState.copy(navRoute = null)
    }

}