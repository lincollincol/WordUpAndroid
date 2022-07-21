package com.linc.wordcard.di.data.database

import android.content.Context
import androidx.room.Room
import com.linc.wordcard.data.database.Database
import com.linc.wordcard.data.database.DatabaseProtocol
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        Database::class.java,
        DatabaseProtocol.DATABASE_NAME
    ).build()

    @Singleton
    @Provides
    fun provideUserDao(database: Database) = database.userDao

}