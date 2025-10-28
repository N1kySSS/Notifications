package com.ortin.notifications

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.core.content.ContextCompat
import com.ortin.notifications.core.NotificationService
import com.ortin.notifications.presentation.auth.AuthorizationViewModel
import com.ortin.notifications.presentation.auth.LoginScreen
import com.ortin.notifications.ui.components.popups.PopUpAskForPermission
import com.ortin.notifications.ui.theme.NotificationsTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {
    private lateinit var notificationService: NotificationService

    private val loginViewModel: AuthorizationViewModel by viewModel()

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission(),
    ) { isGranted: Boolean ->
        if (isGranted) {
            /* do nothing */
        } else {
            Toast.makeText(
                this,
                getString(R.string.toast_about_permission),
                Toast.LENGTH_LONG,
            ).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        notificationService = NotificationService()
        notificationService.getFCMToken()

        setContent {
            val uiState by loginViewModel.uiState.collectAsState()

            askNotificationPermission()

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

                if (loginViewModel.isShowPopUp.value) {
                    PopUpAskForPermission(
                        onButtonClicked = {
                            loginViewModel.isShowPopUp.value = false
                            requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
                        },
                        onDismissRequest = {
                            loginViewModel.isShowPopUp.value = false
                        }
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
                loginViewModel.isShowPopUp.value = true
            } else {
                requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
        }
    }
}
