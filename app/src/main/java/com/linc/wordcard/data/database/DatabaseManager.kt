package com.linc.wordcard.data.database

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DatabaseManager @Inject constructor(
    private val database: Database,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    suspend fun clear() = withContext(ioDispatcher) {
        database.clearAllTables()
    }

}