package com.linc.wordcard.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.linc.wordcard.data.database.DatabaseProtocol
import com.linc.wordcard.data.database.entity.UserEntity

@Dao
abstract class UserDao : BaseDao<UserEntity>() {

    @Query(DatabaseProtocol.UserTable.QUERY_SELECT_BY_ID)
    abstract suspend fun selectById(id: String) : UserEntity?

}