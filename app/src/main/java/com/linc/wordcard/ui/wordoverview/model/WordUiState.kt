package com.linc.wordcard.ui.wordoverview.model

import com.linc.wordcard.entity.word.UserWord
import com.linc.wordcard.extension.EMPTY

data class WordUiState(
    val id: String,
    val word: List<String> = listOf(),
    val translate: List<String> = listOf(),
    val learned: Boolean = false,
    val bookmarked: Boolean = false
)

fun UserWord.toUiState() = WordUiState(
    id = id,
    word = listOf(word),
    translate = translate,
    learned = learned,
    bookmarked = bookmarked
)