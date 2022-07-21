package com.linc.wordcard.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.linc.wordcard.data.database.DatabaseProtocol

@Entity(
    tableName = DatabaseProtocol.UserTable.TABLE_NAME,
    primaryKeys = [ DatabaseProtocol.UserTable.COLUMN_ID ]
)
data class UserEntity(
    @ColumnInfo(name = DatabaseProtocol.UserTable.COLUMN_ID) val id: String,
    @ColumnInfo(name = DatabaseProtocol.UserTable.COLUMN_NAME) val name: String
)