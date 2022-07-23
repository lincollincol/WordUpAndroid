package com.linc.wordcard.entity.collection

import com.linc.wordcard.entity.Word

data class Collection(
    val id: String,
    val name: String,
    val wordsCount: Int,
)