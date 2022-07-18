package com.linc.wordcard.ui.navigation.model

sealed interface NavCommand

object Back : NavCommand
data class BackTo(val route: String, val inclusive: Boolean = false) : NavCommand
data class Forward(val route: String) : NavCommand
data class Replace(val route: String) : NavCommand
data class NewRoot(val route: String) : NavCommand