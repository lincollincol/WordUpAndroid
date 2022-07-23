package com.linc.wordcard.extension

import io.ktor.client.request.forms.*
import io.ktor.http.*
import jodd.net.MimeTypes
import java.io.File

fun Headers.Companion.binaryHeaders(
    mimeType: String,
    name: String,
) = build {
    append(HttpHeaders.ContentType, mimeType)
    append(HttpHeaders.ContentDisposition, "filename=\"$name\"")
}

fun File.toFormPart(key: String) = FormPart(
    key = key,
    value = readBytes(),
    headers = Headers.binaryHeaders(
        MimeTypes.getMimeType(extension),
        nameWithoutExtension
    )
)