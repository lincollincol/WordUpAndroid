package com.linc.wordcard.data.mappers

import com.linc.wordcard.data.network.model.word.UserWordApiModel
import com.linc.wordcard.entity.word.UserWord

fun UserWordApiModel.toModel() = UserWord(
    id = id,
    word = word,
    translate = translate,
    bookmarked = bookmarked,
    learned = learned,
    collectionIndex = collectionIndex
)