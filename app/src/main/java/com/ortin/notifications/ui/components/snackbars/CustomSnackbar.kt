package com.ortin.notifications.ui.components.snackbars

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.ortin.notifications.ui.components.noRippleClickable
import com.ortin.notifications.ui.theme.LocalDimensions

@Composable
internal fun CustomSnackbar(
    message: String,
    messageColor: Color,
    backgroundColor: Color,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
    bottomExternalPadding: Dp = LocalDimensions.current.paddings.paddingL,
    internalPadding: PaddingValues = PaddingValues(0.dp, 0.dp, 0.dp, 0.dp),
    leadingIcon: Painter? = null,
    trailingIcon: Painter? = null,
    textStyle: TextStyle = MaterialTheme.typography.bodyLarge,
) {
    val dimensions = LocalDimensions.current

    Box(
        modifier = modifier
            .padding(bottom = bottomExternalPadding)
            .fillMaxWidth()
            .defaultMinSize(minHeight = dimensions.other.componentMinHeight)
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(dimensions.corners.cornerM)
            )
            .padding(internalPadding)
    ) {
        if (leadingIcon != null) {
            Row(
                modifier = Modifier
                    .padding(horizontal = dimensions.paddings.paddingS)
                    .align(Alignment.CenterStart),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Icon(
                    modifier = Modifier.size(dimensions.iconDimensions.sizeS),
                    painter = leadingIcon,
                    contentDescription = null,
                    tint = messageColor
                )

                Spacer(modifier = Modifier.width(dimensions.paddings.paddingXS))

                Text(
                    modifier = Modifier.weight(1f),
                    text = message,
                    color = messageColor,
                    style = textStyle
                )
            }
            if (trailingIcon != null) {
                Box(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(
                            top = dimensions.paddings.paddingXXS,
                            end = dimensions.paddings.paddingXXS
                        )
                        .size(dimensions.iconDimensions.sizeM)
                        .noRippleClickable {
                            onDismiss()
                        },
                ) {
                    Icon(
                        painter = trailingIcon,
                        contentDescription = null,
                        tint = messageColor
                    )
                }
            }
        } else {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = message,
                color = messageColor,
                style = textStyle
            )
        }
    }
}
