package com.ortin.notifications.domain.repository

import com.ortin.notifications.data.models.Notification

internal interface NotificationRepository {
    suspend fun getNotifications(id: String): List<Notification>
}
