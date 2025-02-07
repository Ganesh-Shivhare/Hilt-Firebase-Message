package com.ganesh.hilt.firebase.messaging.ui.main

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ganesh.hilt.firebase.messaging.databinding.ActivityMainBinding
import com.ganesh.hilt.firebase.messaging.viewmodel.FirebaseViewModel
import com.ganesh.hilt.firebase.messaging.viewmodel.NotificationViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private lateinit var notificationViewModel: NotificationViewModel
    private lateinit var viewModel: FirebaseViewModel
    private lateinit var adapter: NotificationAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        notificationViewModel = ViewModelProvider(this)[NotificationViewModel::class.java]
        adapter = NotificationAdapter { id -> notificationViewModel.markAsRead(id) }

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = this@MainActivity.adapter
        }

        notificationViewModel.notifications.observe(this) { notifications ->
            binding.txtNoDataFound.isVisible = notifications.isEmpty()
            Log.d("MainActivity", "FCM Token: ${notifications.size}")
            adapter.submitList(notifications)
        }

        viewModel = ViewModelProvider(this).get(FirebaseViewModel::class.java)
        viewModel.fcmToken.observe(this) { token ->
            Log.d("MainActivity", "FCM Token: $token")
        }

        viewModel.fetchToken()
    }
}
