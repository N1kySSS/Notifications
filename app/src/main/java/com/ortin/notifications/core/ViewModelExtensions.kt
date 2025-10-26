package com.ortin.notifications.core

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
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
        Log.e("LAUNCH_SAFE", "${error.message}")
        onError?.invoke(error)
    } finally {
        final?.invoke()
    }
}
