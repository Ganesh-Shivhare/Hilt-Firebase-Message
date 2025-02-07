package com.ganesh.hilt.firebase.messaging.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.ganesh.hilt.firebase.messaging.db.NotificationEntity
import com.ganesh.hilt.firebase.messaging.repository.NotificationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotificationViewModel @Inject constructor(private val repository: NotificationRepository) : ViewModel() {

    val notifications: LiveData<List<NotificationEntity>> = repository.getAllNotifications().asLiveData()

    fun markAsRead(id: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            repository.updateClickedStatus(id)
        }
    }
}
