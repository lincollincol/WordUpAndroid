package com.linc.wordcard.entity.word

data class UserWord(
    val id: String,
    val word: String,
    val translate: List<String>,
    val bookmarked: Boolean,
    val learned: Boolean,
    val collectionIndex: Int
)