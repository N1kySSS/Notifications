package com.ortin.notifications.data.models

import kotlinx.serialization.Serializable

@Serializable
internal data class LoginRequest(
    val id: String,
    val password: String
)
