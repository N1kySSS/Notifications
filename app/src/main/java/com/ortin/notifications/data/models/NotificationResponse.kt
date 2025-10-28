package com.ortin.notifications.data.models

import kotlinx.serialization.Serializable

@Serializable
internal data class NotificationResponse(
    val items: List<Notification>
)
