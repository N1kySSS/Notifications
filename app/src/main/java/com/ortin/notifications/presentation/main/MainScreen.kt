package com.ortin.notifications.presentation.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material3.Text
import androidx.compose.material3.Surface
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.ortin.notifications.R
import com.ortin.notifications.ui.components.cards.NotificationCard
import com.ortin.notifications.ui.components.indicators.CustomPullRefreshIndicator
import com.ortin.notifications.ui.components.snackbars.ErrorSnackbar
import com.ortin.notifications.ui.theme.LocalDimensions

@OptIn(ExperimentalMaterialApi::class)
@Composable
internal fun MainScreen(
    items: List<MainScreenViewModel.NotificationItem>,
    isLoading: Boolean,
    isRefreshing: Boolean,
    errorText: String?,
    onRefresh: () -> Unit,
    onErrorDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {
    val dimensions = LocalDimensions.current

    val pullRefreshState = rememberPullRefreshState(
        refreshing = isRefreshing,
        onRefresh = onRefresh
    )

    val paddingModifier = Modifier
        .statusBarsPadding()
        .navigationBarsPadding()

    Surface(modifier = modifier.fillMaxSize()) {
        when {
            isLoading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .then(paddingModifier),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }

            else -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .then(paddingModifier)
                        .pullRefresh(state = pullRefreshState)
                ) {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(dimensions.paddings.paddingS),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        contentPadding = PaddingValues(dimensions.paddings.paddingM)
                    ) {
                        if (items.isEmpty()) {
                            item {
                                Text(
                                    text = stringResource(R.string.notifications_empty_list),
                                    style = MaterialTheme.typography.labelLarge,
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = dimensions.paddings.paddingM)
                                )
                            }
                        } else {
                            items(items, key = { it.id }) { item ->
                                NotificationCard(
                                    date = item.dateTime,
                                    title = item.title,
                                    description = item.description,
                                    isRead = item.isRead,
                                    onClick = item.onClick
                                )
                            }
                        }
                    }

                    if (!errorText.isNullOrBlank()) {
                        ErrorSnackbar(
                            modifier = Modifier.align(Alignment.BottomCenter),
                            message = errorText,
                            onDismiss = onErrorDismiss
                        )
                    }

                    CustomPullRefreshIndicator(
                        refreshing = isRefreshing,
                        state = pullRefreshState,
                        modifier = Modifier.align(Alignment.TopCenter)
                    )
                }
            }
        }
    }
}