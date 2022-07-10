package com.linc.wordcard.data.repository

import android.media.MediaScannerConnection
import android.net.Uri
import com.linc.wordcard.data.utils.XSLSManager
import com.linc.wordcard.entity.Word
import javax.inject.Inject

class DocumentRepository @Inject constructor(
    private val xlsxManager: XSLSManager
) {

    suspend fun loadDocumentWords(uri: Uri): List<Word> = xlsxManager.readDocument(uri)
        .map { Word(id = null, original = it.key, it.value) }

}