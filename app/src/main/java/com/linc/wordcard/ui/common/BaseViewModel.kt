package com.linc.wordcard.ui.common

import androidx.lifecycle.ViewModel

abstract class BaseViewModel<I> : ViewModel() {
    abstract fun obtainIntent(intent: I)
}