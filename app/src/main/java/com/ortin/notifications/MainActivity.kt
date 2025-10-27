package com.ortin.notifications

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.compose.ui.tooling.preview.Preview
import com.ortin.notifications.presentation.auth.AuthorizationViewModel
import com.ortin.notifications.presentation.auth.LoginScreen
import com.ortin.notifications.ui.theme.NotificationsTheme
import kotlinx.coroutines.delay
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.math.log

class MainActivity : ComponentActivity() {

    private val loginViewModel: AuthorizationViewModel by viewModel()

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission(),
    ) { isGranted: Boolean ->
        if (isGranted) {
            /* do nothing */
        } else {
            // TODO: popUp\toast - что пуши не будут работать
        }
    }

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


    private fun askNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) ==
                PackageManager.PERMISSION_GRANTED
            ) {
                /* do nothing */
            } else if (shouldShowRequestPermissionRationale(Manifest.permission.POST_NOTIFICATIONS)) {
                /* TODO: popUp - что пуши не работют и спросиь: хочет ли он дать разрешения
                *  если нажата кнопка да - делать запрос
                *  если нажата кнопка нет или он закрыл popUp - ничего не делать */
            } else {
                requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
        }
    }
}
