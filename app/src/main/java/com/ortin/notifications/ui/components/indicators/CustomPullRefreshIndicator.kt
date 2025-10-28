package com.ortin.notifications.ui.components.indicators

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.PullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterialApi::class)
@Composable
internal fun CustomPullRefreshIndicator(
    refreshing: Boolean,
    state: PullRefreshState,
    modifier: Modifier = Modifier
) {
    val colors = MaterialTheme.colorScheme
    val isLight = isSystemInDarkTheme()

    PullRefreshIndicator(
        refreshing = refreshing,
        state = state,
        modifier = modifier,
        contentColor = if (isLight) colors.primary else colors.secondary,
        backgroundColor = if (isLight) colors.surface else colors.surfaceVariant.copy(alpha = 0.8f)
    )
}
