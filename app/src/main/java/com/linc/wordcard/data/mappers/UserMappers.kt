package com.linc.wordcard.data.mappers

import com.linc.wordcard.data.database.entity.UserEntity
import com.linc.wordcard.data.network.model.user.UserApiModel

fun UserApiModel.toEntity() = UserEntity(id, name)