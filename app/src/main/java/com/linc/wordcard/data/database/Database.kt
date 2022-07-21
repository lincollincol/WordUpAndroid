package com.linc.wordcard.data.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.linc.wordcard.data.database.dao.UserDao
import com.linc.wordcard.data.database.entity.UserEntity

@Database(
    version = 1,
    exportSchema = false,
    entities = [
        UserEntity::class
    ]
)
abstract class Database : RoomDatabase() {

    abstract val userDao: UserDao

}