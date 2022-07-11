package com.linc.wordcard.data.network.model

import kotlinx.serialization.Serializable

@Serializable
data class UserApiModel(
    val id: String,
    val name: String
)