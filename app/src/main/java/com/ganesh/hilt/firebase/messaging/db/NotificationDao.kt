package com.ganesh.hilt.firebase.messaging.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface NotificationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNotification(notification: NotificationEntity): Long // Return inserted row ID

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNotifications(notifications: List<NotificationEntity>): List<Long>

    @Query("SELECT * FROM notifications ORDER BY dateTime DESC")
    fun getAllNotifications(): Flow<List<NotificationEntity>>

    @Query("UPDATE notifications SET isClicked = 1 WHERE id = :id")
    fun updateClickedStatus(id: Int): Int  // Return Int (number of rows updated)

}
