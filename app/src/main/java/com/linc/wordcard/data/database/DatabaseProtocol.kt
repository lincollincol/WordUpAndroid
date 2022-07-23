package com.linc.wordcard.data.database

object DatabaseProtocol {

    const val DATABASE_NAME = "word_up_database"

    object UserTable {
        const val TABLE_NAME = "users"
        const val COLUMN_ID = "id"
        const val COLUMN_NAME = "name"

        const val QUERY_SELECT_BY_ID = "select * from $TABLE_NAME where $COLUMN_ID = :id"
    }

}