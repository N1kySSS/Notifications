package com.ortin.notifications.presentation.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import com.ortin.notifications.R
import com.ortin.notifications.core.LightModePreview
import com.ortin.notifications.core.NightModePreview
import com.ortin.notifications.ui.components.snackbars.ErrorSnackbar
import com.ortin.notifications.ui.theme.LocalDimensions
import com.ortin.notifications.ui.theme.NotificationsTheme

@Composable
internal fun LoginScreen(
    login: String,
    onLoginChange: (String) -> Unit,
    password: String,
    onPasswordChange: (String) -> Unit,
    onLoginClick: () -> Unit,
    isLoading: Boolean,
    errorText: String?,
    onErrorDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {
    val dimensions = LocalDimensions.current
    val focusManager = LocalFocusManager.current
    var passwordVisible by remember { mutableStateOf(false) }
    val scrollState = rememberScrollState()

    val iconColors = IconButtonDefaults.iconButtonColors(
        contentColor = MaterialTheme.colorScheme.primary
    )

    Box(
        modifier = modifier
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding()
            .padding(horizontal = dimensions.paddings.paddingM),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(scrollState)
                .imePadding(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier
                    .padding(
                        top = dimensions.paddings.paddingXL,
                        bottom = dimensions.paddings.paddingL
                    ),
                text = stringResource(R.string.login_screen_title),
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = dimensions.paddings.paddingXXS),
                value = login,
                onValueChange = onLoginChange,
                label = { Text(text = stringResource(R.string.login_screen_id)) },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                )
            )

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = dimensions.paddings.paddingXXS),
                value = password,
                onValueChange = onPasswordChange,
                label = { Text(text = stringResource(R.string.login_screen_password)) },
                trailingIcon = {
                    val icon = if (passwordVisible) {
                        Icons.Default.VisibilityOff
                    } else {
                        Icons.Default.Visibility
                    }

                    IconButton(
                        colors = iconColors,
                        onClick = { passwordVisible = !passwordVisible }
                    ) {
                        Icon(icon, contentDescription = null)
                    }
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() })
            )

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = dimensions.paddings.paddingM)
                    .height(dimensions.other.componentMinHeight),
                onClick = onLoginClick,
                enabled = !isLoading && login.isNotBlank() && password.isNotBlank(),
                shape = RoundedCornerShape(dimensions.corners.cornerM)
            ) {
                Text(
                    text = stringResource(R.string.login_screen_login_button),
                    style = MaterialTheme.typography.labelLarge
                )
            }
        }

        if (isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White.copy(alpha = 0.6f))
                    .align(Alignment.Center)
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center),
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }

        if (!errorText.isNullOrBlank()) {
            ErrorSnackbar(
                modifier = Modifier.align(Alignment.BottomCenter),
                message = errorText,
                onDismiss = onErrorDismiss
            )
        }
    }
}

@LightModePreview
@NightModePreview
@Preview
@Composable
private fun LoginScreenPreview() {
    var login by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    NotificationsTheme {
        LoginScreen(
            login = login,
            onLoginChange = { login = it },
            password = password,
            onPasswordChange = { password = it },
            isLoading = false,
            errorText = null,
            onLoginClick = {},
            onErrorDismiss = {}
        )
    }
}
