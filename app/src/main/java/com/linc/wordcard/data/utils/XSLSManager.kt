package com.linc.wordcard.data.utils

import android.content.Context
import android.net.Uri
import androidx.annotation.UiThread
import com.linc.wordcard.extension.createTempFile
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import okhttp3.internal.wait
import org.apache.poi.hssf.usermodel.HSSFWorkbook
import org.apache.poi.ss.usermodel.Cell
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.ss.usermodel.WorkbookFactory
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.io.FileInputStream
import java.io.FileOutputStream
import java.net.URI
import javax.annotation.concurrent.ThreadSafe
import javax.inject.Inject

class XSLSManager @Inject constructor(
    @ApplicationContext private val context: Context,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    companion object {
        private const val WORD_ORIGINAL_SHEET = "original"
        private const val WORD_TRANSLATE_SHEET = "translate"
    }

    suspend fun readDocument(
        uri: Uri
    ): Map<String, List<String>> = withContext(ioDispatcher) {
        return@withContext context.contentResolver
            .openInputStream(uri)
            ?.use { stream ->
                val book = WorkbookFactory.create(stream)
                val wordsSheet = book.getSheet(WORD_ORIGINAL_SHEET)
                val translateSheet = book.getSheet(WORD_TRANSLATE_SHEET)
                val words = async { readSheetRows(wordsSheet).mapNotNull { it.firstOrNull() } }
                val translate = async { readSheetRows(translateSheet).map(List<String>::distinct) }
                words.await().zip(translate.await()).toMap()
            }
            ?: emptyMap()
    }

    private suspend fun readSheetRows(
        sheet: Sheet
    ): List<List<String>> = withContext(ioDispatcher) {
        val rows = mutableListOf<List<String>>()
        for(row in sheet) {
            rows.add(readRowCells(row))
        }
        return@withContext rows
    }

    private fun readRowCells(row: Row): List<String> {
        val cells = mutableListOf<String?>()
        for(cell in row) {
            if(cell.cellType == Cell.CELL_TYPE_STRING) {
                cells.add(cell.stringCellValue)
            }
        }
        return cells.filterNotNull()
    }
}