package com.ortin.notifications.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class Notification(

    @SerialName("messageId")
    val id: String,

    @SerialName("title")
    val title: String,

    @SerialName("description")
    val description: String?,

    @SerialName("imageUrl")
    val imageUrl: String?,

    /* LocalDateTime in epochMillis*/
    @SerialName("dateTime")
    val dateTime: Long,

    @SerialName("isRead")
    val isRead: Boolean,

    @SerialName("incidentType")
    val incidentType: String
)
