package com.ortin.notifications.ui.components.spacers

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp

@Composable
internal fun VerticalSpacer(
    height: Dp,
    modifier: Modifier = Modifier
) {
    Spacer(modifier = modifier.height(height))
}
