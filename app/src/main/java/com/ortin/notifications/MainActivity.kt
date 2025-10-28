package com.ortin.notifications

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.ortin.notifications.presentation.main.MainScreen
import com.ortin.notifications.presentation.main.MainScreenViewModel
import com.ortin.notifications.ui.theme.NotificationsTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val notificationsViewModel: MainScreenViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val uiState by notificationsViewModel.uiState.collectAsState()

            var first by remember { mutableStateOf(true) }

            NotificationsTheme {
                MainScreen(
                    items = if (first) { emptyList() } else { uiState.notifications },
                    isLoading = uiState.loading,
                    isRefreshing = uiState.isRefreshing,
                    errorText = uiState.errorText,
                    onRefresh = {
                        notificationsViewModel.loadData()
                        first = false
                    },
                    onErrorDismiss = {}
                )
            }
        }
    }
}
