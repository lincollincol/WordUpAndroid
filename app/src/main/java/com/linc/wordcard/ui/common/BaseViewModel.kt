package com.linc.wordcard.ui.common

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.linc.wordcard.ui.navigation.model.*
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import timber.log.Timber

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

    protected open fun handleError(error: Throwable) {
        Timber.e(error)
    }

    protected fun launchCoroutine(block: suspend CoroutineScope.() -> Unit) {
        viewModelScope.launch(
            context = CoroutineExceptionHandler { _, throwable ->
                handleError(throwable)
            },
            block = block
        )
    }

    fun observeNavRoute(onNavigate: (NavCommand) -> Unit) {
        navCommand?.let {
            onNavigate(it)
            navCommand = null
        }
    }

}