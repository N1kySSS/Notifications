package com.ortin.notifications.data.impl

import com.ortin.notifications.data.datastore.UserPreferences
import com.ortin.notifications.data.models.LoginRequest
import com.ortin.notifications.domain.repository.AuthorizationRepository
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpStatusCode

internal class AuthorizationRepositoryImpl(
    private val client: HttpClient,
    private val preferences: UserPreferences
) : AuthorizationRepository {

    override suspend fun login(id: String, password: String): String {
        val response: HttpResponse = client.post("/login") {
            setBody(LoginRequest(id, password))
        }

        val result = when (response.status) {
            HttpStatusCode.OK -> response.body<String>()
            HttpStatusCode.NotFound -> throw Exception("Пользователь не найден")
            HttpStatusCode.Unauthorized -> throw Exception("Неверный логин или пароль")
            else -> throw Exception("Сервер не отвечает")
        }

        preferences.saveUser(id, result)

        return result
    }
}
