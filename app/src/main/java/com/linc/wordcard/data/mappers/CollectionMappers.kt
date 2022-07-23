package com.linc.wordcard.data.mappers

import com.linc.wordcard.data.network.model.collection.CollectionApiModel
import com.linc.wordcard.entity.collection.Collection

fun CollectionApiModel.toModel() = Collection(
    id = id,
    name = name,
    wordsCount = wordsCount
)