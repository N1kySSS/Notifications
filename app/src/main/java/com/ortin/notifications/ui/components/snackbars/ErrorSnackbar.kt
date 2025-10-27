package com.ortin.notifications.ui.components.snackbars

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import com.ortin.notifications.ui.theme.LocalDimensions

@Composable
internal fun ErrorSnackbar(
    message: String,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
    leadingIcon: Painter? = null,
    trailingIcon: Painter? = null,
) {
    val dimensions = LocalDimensions.current

    val textColor = Color(0xFFEE255C)
    val background = Color(0xFFFFE8E8)

    CustomSnackbar(
        modifier = modifier,
        message = message,
        messageColor = textColor,
        backgroundColor = background,
        onDismiss = onDismiss,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        bottomExternalPadding = dimensions.paddings.paddingL
    )
}
