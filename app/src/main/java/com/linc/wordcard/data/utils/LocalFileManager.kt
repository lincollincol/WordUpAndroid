package com.linc.wordcard.data.utils

import android.content.Context
import android.net.Uri
import com.linc.wordcard.extension.createTempFile
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class LocalFileManager @Inject constructor(
    @ApplicationContext private val context: Context
) {

    suspend fun getTempFile(uri: Uri) = context.createTempFile(uri)

}