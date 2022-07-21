package com.linc.wordcard.ui.signup

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.linc.wordcard.data.repository.AuthRepository
import com.linc.wordcard.data.repository.UsersRepository
import com.linc.wordcard.ui.common.BaseViewModel
import com.linc.wordcard.ui.navigation.model.AppScreen
import com.linc.wordcard.ui.signup.model.*
import com.linc.wordcard.ui.signup.model.SignUpUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : BaseViewModel<SignUpUiState, SignUpIntent>() {

    override var uiState: SignUpUiState by mutableStateOf(SignUpUiState())
        private set

    override fun obtainIntent(intent: SignUpIntent) {
        when(intent) {
            is NameChange -> updateName(intent.value)
            is LoginChange -> updateLogin(intent.value)
            is PasswordChange -> updatePassword(intent.value)
            is SignUpClick -> handleSignUp()
            is SignInClick -> handleSignIn()
        }
    }

    private fun updateName(name: String) {
        uiState = uiState.copy(name = name)
    }

    private fun updateLogin(login: String) {
        uiState = uiState.copy(login = login)
    }

    private fun updatePassword(password: String) {
        uiState = uiState.copy(password = password)
    }

    private fun handleSignUp() {
        viewModelScope.launch {
            try {
                authRepository.signUp(
                    name = uiState.name,
                    login = uiState.login,
                    password = uiState.password,
                )
                navigateToNewRoot(AppScreen.Main.route)
            } catch (e: Exception) {
                Timber.e(e)
            }
        }
    }

    private fun handleSignIn() {
        navigateBack()
    }

}