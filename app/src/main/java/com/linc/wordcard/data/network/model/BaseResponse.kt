package com.linc.wordcard.data.network.model

import kotlinx.serialization.Serializable

@Serializable
data class BaseResponse<T>(
    val data: T?
)