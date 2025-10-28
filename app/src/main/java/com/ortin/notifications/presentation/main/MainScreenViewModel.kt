package com.ortin.notifications.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ortin.notifications.core.launchSafe
import com.ortin.notifications.core.toFormattedDateTime
import com.ortin.notifications.data.models.Notification
import com.ortin.notifications.domain.usecase.GetNotificationsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

internal class MainScreenViewModel(
    private val getNotificationsUseCase: GetNotificationsUseCase
) : ViewModel() {

    data class MainScreenState(
        val notifications: List<NotificationItem> = emptyList(),
        val loading: Boolean = false,
        val isRefreshing: Boolean = false,
        val errorText: String? = null
    )

    private val _uiState = MutableStateFlow<MainScreenState>(MainScreenState())
    val uiState: StateFlow<MainScreenState> = _uiState.asStateFlow()

    init {
        loadData(false)
    }

    fun loadData(force: Boolean = true) {
        launchSafe(
            start = {
                _uiState.update {
                    if (force) {
                        it.copy(isRefreshing = true)
                    } else {
                        it.copy(loading = true)
                    }
                }
            },
            body = {
                _uiState.update {
                    it.copy(
                        notifications = getNotificationsUseCase(Unit)
                            .getOrThrow()
                            .sortedByDescending { item -> item.dateTime }
                            .toItems()
                    )
                }
            },
            onError = { throwable -> _uiState.update { it.copy(errorText = throwable.message) } },
            final = {
                _uiState.update {
                    if (force) {
                        it.copy(isRefreshing = false)
                    } else {
                        it.copy(loading = false)
                    }
                }
            }
        )
    }

    data class NotificationItem(
        val id: String,
        val title: String,
        val description: String,
        val imageUrl: String,
        val dateTime: String,
        val isRead: Boolean,
        val incidentType: String,
        val onClick: () -> Unit
    )

    private fun Notification.toItem() = NotificationItem(
        id = id,
        title = title,
        description = description.orEmpty(),
        imageUrl = imageUrl.orEmpty(),
        dateTime = dateTime.toFormattedDateTime(),
        isRead = isRead,
        incidentType = incidentType,
        onClick = { /* TODO: add navigation to detailed screen in the future */ }
    )

    private fun List<Notification>.toItems() = map { it.toItem() }
}
