package com.linc.wordcard.ui.common

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import com.linc.wordcard.ui.navigation.model.*

abstract class BaseViewModel<U, I> : ViewModel() {

    abstract val uiState: U

    var navCommand: NavCommand? by mutableStateOf(null)
        private set

    abstract fun obtainIntent(intent: I)

    protected open fun navigateTo(route: String, replace: Boolean = false) {
        this.navCommand = if(replace) Replace(route) else Forward(route)
    }

    protected open fun navigateBack(route: String? = null) {
        this.navCommand = route?.let(::BackTo) ?: Back
    }

    protected open fun navigateToNewRoot(route: String) {
        this.navCommand = NewRoot(route)
    }

    fun observeNavRoute(onNavigate: (NavCommand) -> Unit) {
        navCommand?.let {
            onNavigate(it)
            navCommand = null
        }
    }

}