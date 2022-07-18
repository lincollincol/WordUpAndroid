package com.linc.wordcard.ui.signin.model

import com.linc.wordcard.extension.EMPTY

data class SignInUiState(
    val login: String = String.EMPTY,
    val password: String = String.EMPTY,
)