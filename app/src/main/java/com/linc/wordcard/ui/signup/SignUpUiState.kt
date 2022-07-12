package com.linc.wordcard.ui.signup

import com.linc.wordcard.extension.EMPTY

data class SignUpUiState(
    val login: String = String.EMPTY,
    val password: String = String.EMPTY,
    val name: String = String.EMPTY,
)