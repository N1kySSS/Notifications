package com.ortin.notifications.domain.usecase

import com.ortin.notifications.core.UseCase
import com.ortin.notifications.data.models.Notification
import com.ortin.notifications.domain.repository.NotificationRepository

internal class GetNotificationsUseCase(
    private val repository: NotificationRepository
) : UseCase<String, List<Notification>>() {
    override suspend fun execute(params: String): List<Notification> {
        return repository.getNotifications(params)
    }
}
