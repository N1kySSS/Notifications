package com.ortin.notifications.domain.usecase

import com.ortin.notifications.core.UseCase
import com.ortin.notifications.domain.repository.AuthorizationRepository

internal class LoginUseCase(
    private val repository: AuthorizationRepository
) : UseCase<LoginUseCase.Params, String>() {
    override suspend fun execute(params: Params): String = repository.login(
        id = params.id,
        password = params.password
    )

    data class Params(
        val id: String,
        val password: String
    )
}
