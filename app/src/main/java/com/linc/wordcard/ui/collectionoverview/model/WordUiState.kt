package com.linc.wordcard.ui.collectionoverview.model

import com.linc.wordcard.entity.word.UserWord
import com.linc.wordcard.extension.EMPTY

data class WordUiState(
    val word: List<String> = listOf(),
    val translate: List<String> = listOf(),
    val learned: Boolean = false,
    val bookmarked: Boolean = false
)

fun UserWord.toUiState() = WordUiState(
    word = listOf(word),
    translate = translate,
    learned = learned,
    bookmarked = bookmarked
)