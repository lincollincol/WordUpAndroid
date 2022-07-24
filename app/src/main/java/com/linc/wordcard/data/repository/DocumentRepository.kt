package com.linc.wordcard.data.repository

import android.net.Uri
import com.linc.wordcard.data.utils.XSLSManager
import com.linc.wordcard.entity.word.Word
import com.linc.wordcard.extension.EMPTY
import javax.inject.Inject

class DocumentRepository @Inject constructor(
    private val xlsxManager: XSLSManager
) {

    suspend fun loadDocumentWords(uri: Uri): List<Word> = xlsxManager.readDocument(uri)
        .map { Word(id = String.EMPTY, word = it.key, translate = it.value) }

}