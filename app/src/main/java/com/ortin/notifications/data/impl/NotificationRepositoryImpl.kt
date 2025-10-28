package com.ortin.notifications.data.impl

import com.ortin.notifications.data.datastore.UserPreferences
import com.ortin.notifications.data.models.Notification
import com.ortin.notifications.data.models.NotificationResponse
import com.ortin.notifications.domain.repository.NotificationRepository
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.accept
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.http.headers
import kotlinx.coroutines.flow.first

internal class NotificationRepositoryImpl(
    private val client: HttpClient,
    private val preferences: UserPreferences
) : NotificationRepository {

    override suspend fun getNotifications(): Result<List<Notification>> {
        return runCatching {
            val userId = preferences.userId.first()

            if (userId.isNullOrEmpty()) { throw IllegalArgumentException("Сессия не действительна") }

            val response: NotificationResponse = client.get("pushes/me/") {
                headers {
                    append("user_id", userId)
                    accept(ContentType.Application.Json)
                }
            }.body()

            response.items
        }
    }
}
