package com.linc.wordcard.ui.splash

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.linc.wordcard.data.repository.AuthRepository
import com.linc.wordcard.ui.common.BaseViewModel
import com.linc.wordcard.ui.common.EmptyIntent
import com.linc.wordcard.ui.common.EmptyUiState
import com.linc.wordcard.ui.navigation.model.AppScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : BaseViewModel<EmptyUiState, EmptyIntent>() {

    override val uiState: EmptyUiState by mutableStateOf(EmptyUiState())

    init {
        checkUserLoggedIn()
    }

    override fun obtainIntent(intent: EmptyIntent) {
        // Not implemented
    }

    private fun checkUserLoggedIn() {
        viewModelScope.launch {
            try {
                val authorized = authRepository.isLoggedIn()
                val screen = when {
                    authorized -> AppScreen.Main.route
                    else -> AppScreen.SignIn.route
                }
                navigateTo(route = screen, replace = true)
            } catch (e: Exception) {
                Timber.e(e)
            }
        }
    }
}