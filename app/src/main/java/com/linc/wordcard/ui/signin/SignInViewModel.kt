package com.linc.wordcard.ui.signin

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.linc.wordcard.data.repository.AuthRepository
import com.linc.wordcard.data.repository.UsersRepository
import com.linc.wordcard.ui.common.BaseViewModel
import com.linc.wordcard.ui.navigation.model.AppScreen
import com.linc.wordcard.ui.signin.model.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val usersRepository: UsersRepository,
) : BaseViewModel<SignInUiState, SignInIntent>() {

    override var uiState: SignInUiState by mutableStateOf(SignInUiState())
        private set

    override fun obtainIntent(intent: SignInIntent) {
        when(intent) {
            is LoginChange -> updateLogin(intent.value)
            is PasswordChange -> updatePassword(intent.value)
            is SignUpClick -> handleSignUp()
            is SignInClick -> handleSignIn()
        }
    }

    private fun updateLogin(login: String) {
        uiState = uiState.copy(login = login)
    }

    private fun updatePassword(password: String) {
        uiState = uiState.copy(password = password)
    }

    private fun handleSignIn() {
        viewModelScope.launch {
            try {
                authRepository.signIn(
                    login = uiState.login,
                    password = uiState.password,
                )
                navigateToNewRoot(AppScreen.Main.route)
            } catch (e: Exception) {
                Timber.e(e)
            }
        }
    }

    private fun handleSignUp() {
        navigateTo(AppScreen.SignUp.route)
    }

}