package com.linc.wordcard.entity.word

data class Word(
    val id: String,
    val word: String,
    val translate: List<String>
)