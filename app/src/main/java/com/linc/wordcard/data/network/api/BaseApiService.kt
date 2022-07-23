package com.linc.wordcard.data.network.api

import com.linc.wordcard.data.network.model.BaseResponse
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.request.*
import io.ktor.client.request.forms.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.http.content.*
import java.io.File

abstract class BaseApiService(
    protected val client: HttpClient
) {

    protected suspend inline fun <reified R> multipart(
        path: String,
        parts: List<FormPart<*>>
    ): Result<R?> = safeRequest {
        client.submitFormWithBinaryData(
            formData = formData { parts.forEach { append(it) } }
        ) {
            url.path(path)
        }
    }

    protected suspend inline fun <reified R, reified B> post(
        path: String,
        body: B
    ): Result<R?> = safeRequest {
        client.post {
            url.path(path)
            setBody(body)
        }
    }

    protected suspend inline fun <reified R, reified B> put(
        path: String,
        body: B
    ): Result<R?> = safeRequest {
        client.put {
            url.path(path)
            setBody(body)
        }
    }

    protected suspend inline fun <reified R> get(
        path: String,
        parameters: Map<String, String> = emptyMap()
    ): Result<R?> = safeRequest {
        client.get {
            url.path(path)
            parameters.forEach { url.parameters.append(it.key, it.value) }
        }
    }

    protected suspend inline fun <reified R> delete(
        path: String,
        parameters: Map<String, String> = emptyMap(),
    ): Result<R?> = safeRequest {
        client.delete {
            url.path(path)
            parameters.forEach { url.parameters.append(it.key, it.value) }
        }
    }

    protected suspend inline fun <reified R : Any> safeRequest(
        request: () -> HttpResponse
    ): Result<R?> {
        return try {
            val response = request.invoke().body<BaseResponse<R>>()
            Result.success(response.data)
        } catch (exception: ClientRequestException) {
            val response = exception.response.body<BaseResponse<String>>()
            Result.failure(Exception(response.data))
        }
    }

}