package com.linc.wordcard.entity

data class WordsCollection(
    val id: String?,
    val name: String,
    val words: List<Word>,
)