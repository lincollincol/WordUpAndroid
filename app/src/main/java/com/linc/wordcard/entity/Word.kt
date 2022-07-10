package com.linc.wordcard.entity

data class Word(
    val id: String?,
    val original: String,
    val translateVariants: List<String>
)