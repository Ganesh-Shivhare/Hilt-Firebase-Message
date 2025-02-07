package com.ganesh.hilt.firebase.messaging.ui.main

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ganesh.hilt.firebase.messaging.databinding.ItemNotificationBinding
import com.ganesh.hilt.firebase.messaging.db.NotificationEntity

class NotificationAdapter(private val onItemClick: (Int) -> Unit) :
    RecyclerView.Adapter<NotificationAdapter.ViewHolder>() {

    private val notifications = mutableListOf<NotificationEntity>()

    fun submitList(newList: List<NotificationEntity>) {
        notifications.clear()
        notifications.addAll(newList)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemNotificationBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(notification: NotificationEntity) {
            binding.tvTitle.text = notification.title
            binding.tvMessage.text = notification.message
            binding.tvDate.text = notification.dateTime
            binding.root.alpha = if (notification.isClicked) 0.5f else 1.0f

            binding.root.setOnClickListener {
                onItemClick(notification.id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemNotificationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(notifications[position])
    }

    override fun getItemCount(): Int = notifications.size
}
