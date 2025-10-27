package com.ortin.notifications.presentation.auth

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import com.ortin.notifications.core.launchSafe
import com.ortin.notifications.domain.usecase.LoginUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

internal class AuthorizationViewModel(
    private val loginUseCase: LoginUseCase
) : ViewModel() {
    data class AuthorizationState(
        val id: String = "",
        val password: String = "",
        val loading: Boolean = false,
        val errorText: String? = null
    )

    private val _uiState = MutableStateFlow<AuthorizationState>(AuthorizationState())
    val uiState: StateFlow<AuthorizationState> = _uiState.asStateFlow()

    val isShowPopUp = mutableStateOf(false)

    fun changeId(newValue: String) = _uiState.update { it.copy(id = newValue) }

    fun changePassword(newValue: String) = _uiState.update { it.copy(password = newValue) }

    fun login() {
        launchSafe(
            start = { _uiState.update { it.copy(loading = true) } },
            body = {

                // TODO: replace delay with the commented code when base url will appear
                delay(2000)

//                val result = loginUseCase(
//                    LoginUseCase.Params(
//                        id = _uiState.value.id,
//                        password = _uiState.value.password
//                    )
//                )
//
//                if (result.isNotEmpty()) {
//                    // TODO: save to store; navigate the main
//                }
            },
            onError = { throwable -> _uiState.update { it.copy(errorText = throwable.message) } },
            final = { _uiState.update { it.copy(loading = false) } }
        )
    }
}
