package com.linc.wordcard.ui.signup.model

sealed interface SignUpIntent

data class NameChange(val value: String) : SignUpIntent
data class LoginChange(val value: String) : SignUpIntent
data class PasswordChange(val value: String) : SignUpIntent
object SignUpClick : SignUpIntent
object SignInClick : SignUpIntent
object FinishNavigation : SignUpIntent