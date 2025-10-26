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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ortin.notifications.presentation.auth.LoginScreen
import com.ortin.notifications.ui.theme.NotificationsTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var login by remember { mutableStateOf("") }
            var password by remember { mutableStateOf("") }
            var loading by remember { mutableStateOf(false) }
            var error by remember { mutableStateOf<String?>(null) }

            LaunchedEffect(loading) {
                if (loading) {
                    delay(2000)
                    loading = false
                    error = "Пароль неверный"
                }
            }

            NotificationsTheme {
                LoginScreen(
                    login = login,
                    onLoginChange = { login = it; error = null },
                    password = password,
                    onPasswordChange = { password = it; error = null },
                    isLoading = loading,
                    errorText = error,
                    onLoginClick = { loading = true },
                    onErrorDismiss = {}
                )
            }
        }
    }
}
