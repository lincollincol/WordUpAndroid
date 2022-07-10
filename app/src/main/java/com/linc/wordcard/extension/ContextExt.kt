package com.linc.wordcard.extension

import android.content.Context
import android.net.Uri
import androidx.core.net.toUri
import java.io.File
import java.util.*

fun Context.createTempUri(): Uri {
    return createTempFile().toUri()
}

fun Context.createTempFile(extension: String? = null): File {
    val temp = File(this.cacheDir, "${UUID.randomUUID()}${extension?.let { ".$it" }.orEmpty()}")
    temp.createNewFile()
    temp.deleteOnExit()
    return temp
}

fun Context.createTempFile(uri: Uri): File? {
    val data = uri.getFileBytes(this) ?: return null
    val extension = uri.getFileExtension(this)
    return createTempFile(extension).also { file -> file.writeBytes(data) }
}
