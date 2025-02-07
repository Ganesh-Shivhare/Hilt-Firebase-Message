package com.ganesh.hilt.firebase.messaging.di

import android.content.Context
import androidx.room.Room
import com.ganesh.hilt.firebase.messaging.db.NotificationDao
import com.ganesh.hilt.firebase.messaging.db.NotificationDatabase
import com.ganesh.hilt.firebase.messaging.repository.NotificationRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): NotificationDatabase {
        return Room.databaseBuilder(
            context,
            NotificationDatabase::class.java,
            "notifications_db"
        ).allowMainThreadQueries().build()
    }

    @Provides
    fun provideDao(database: NotificationDatabase): NotificationDao {
        return database.notificationDao()
    }

    @Provides
    fun provideRepository(dao: NotificationDao): NotificationRepository {
        return NotificationRepository(dao)
    }
}
