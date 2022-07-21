package com.linc.wordcard.data.database.dao

import androidx.room.Dao
import com.linc.wordcard.data.database.entity.UserEntity

@Dao
abstract class UserDao : BaseDao<UserEntity>()