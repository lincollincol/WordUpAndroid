package com.linc.wordcard.ui.word.model

sealed interface WordIntent

data class LoadWord(val id: String) : WordIntent