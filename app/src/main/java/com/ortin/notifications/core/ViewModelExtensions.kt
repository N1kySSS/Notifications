package com.ortin.notifications.core

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Job
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

fun ViewModel.launchSafe(
    body: suspend () -> Unit,
    onError: ((error: Throwable) -> Unit)? = null,
    start: (() -> Unit)? = null,
    final: (() -> Unit)? = null
): Job = viewModelScope.launch {
    try {
        start?.invoke()
        body()
    } catch (error: Exception) {
        if (!isActive && error is CancellationException) {
            Log.w("LAUNCH_SAFE", "${error.message}")
            throw error
        }

        Log.e("LAUNCH_SAFE", "${error.message}")
        onError?.invoke(error)
    } finally {
        final?.invoke()
    }
}
