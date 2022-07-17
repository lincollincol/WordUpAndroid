package com.linc.wordcard.ui.signin.model

sealed interface SignInIntent

data class LoginChange(val value: String) : SignInIntent
data class PasswordChange(val value: String) : SignInIntent
object SignUpClick : SignInIntent
object SignInClick : SignInIntent
object FinishNavigation : SignInIntent