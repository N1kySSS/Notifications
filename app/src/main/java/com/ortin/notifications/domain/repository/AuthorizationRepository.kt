package com.ortin.notifications.domain.repository

internal interface AuthorizationRepository {
    suspend fun login(id: String, password: String): String
}
