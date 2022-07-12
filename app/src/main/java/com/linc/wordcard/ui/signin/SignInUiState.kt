package com.linc.wordcard.ui.signin

import com.linc.wordcard.extension.EMPTY

data class SignInUiState(
    val login: String = String.EMPTY,
    val password: String = String.EMPTY,
)