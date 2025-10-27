package com.ortin.notifications

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ortin.notifications.presentation.auth.AuthorizationViewModel
import com.ortin.notifications.presentation.auth.LoginScreen
import com.ortin.notifications.ui.theme.NotificationsTheme
import kotlinx.coroutines.delay
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.math.log

class MainActivity : ComponentActivity() {

    private val loginViewModel: AuthorizationViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val uiState by loginViewModel.uiState.collectAsState()

            NotificationsTheme {

                // TODO: replace with navigation in the future
                LoginScreen(
                    login = uiState.id,
                    onLoginChange = { loginViewModel.changeId(it) },
                    password = uiState.password,
                    onPasswordChange = { loginViewModel.changePassword(it) },
                    isLoading = uiState.loading,
                    errorText = uiState.errorText,
                    onLoginClick = loginViewModel::login,
                    onErrorDismiss = {}
                )
            }
        }
    }
}
