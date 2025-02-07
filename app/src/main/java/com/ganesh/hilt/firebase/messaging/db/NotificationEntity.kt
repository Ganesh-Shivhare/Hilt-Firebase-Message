package com.ganesh.hilt.firebase.messaging.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notifications")
data class NotificationEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val message: String,
    val dateTime: String,
    val isClicked: Boolean = false
)
