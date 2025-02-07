package com.ganesh.hilt.firebase.messaging.repository

import com.ganesh.hilt.firebase.messaging.db.NotificationDao
import com.ganesh.hilt.firebase.messaging.db.NotificationEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NotificationRepository @Inject constructor(private val dao: NotificationDao) {

    fun getAllNotifications(): Flow<List<NotificationEntity>> = dao.getAllNotifications()

    suspend fun insertNotification(notification: NotificationEntity) {
        dao.insertNotification(notification)
    }

    suspend fun updateClickedStatus(id: Int) {
        dao.updateClickedStatus(id)
    }
}
