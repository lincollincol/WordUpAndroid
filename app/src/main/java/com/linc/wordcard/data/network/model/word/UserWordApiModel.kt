package com.linc.wordcard.data.network.model.word

data class UserWordApiModel(
    val id: String,
    val word: String,
    val translate: List<String>,
    val bookmarked: Boolean,
    val learned: Boolean,
    val collectionIndex: Int
)