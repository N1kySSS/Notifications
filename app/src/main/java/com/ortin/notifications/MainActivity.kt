package com.ortin.notifications

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import com.ortin.notifications.presentation.auth.AuthorizationViewModel
import com.ortin.notifications.presentation.auth.LoginScreen
import com.ortin.notifications.presentation.main.MainScreen
import com.ortin.notifications.presentation.main.MainScreenViewModel
import com.ortin.notifications.ui.components.cards.NotificationCard
import com.ortin.notifications.ui.theme.NotificationsTheme
import com.ortin.notifications.ui.utils.Constants
import kotlinx.coroutines.delay
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale
import kotlin.math.log

class MainActivity : ComponentActivity() {

    private val loginViewModel: AuthorizationViewModel by viewModel()
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
