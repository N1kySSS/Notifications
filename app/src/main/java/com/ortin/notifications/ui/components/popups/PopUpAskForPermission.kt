package com.ortin.notifications.ui.components.popups

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import com.ortin.notifications.R
import com.ortin.notifications.ui.theme.ErrorColor
import com.ortin.notifications.ui.theme.LocalDimensions

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PopUpAskForPermission(
    onButtonClicked: () -> Unit,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier
) {
    val dimensions = LocalDimensions.current
    val shape = RoundedCornerShape(dimensions.corners.cornerM)
    val border = BorderStroke(
        width = dimensions.border.popupBorderWidth,
        color = MaterialTheme.colorScheme.primary
    )

    BasicAlertDialog(
        modifier = modifier
            .height(dimensions.other.popUpHeight)
            .clip(shape)
            .background(
                color = Color.White,
                shape = shape
            ),
        onDismissRequest = { onDismissRequest() }
    ) {
        Column(
            modifier = Modifier
                .padding(dimensions.paddings.paddingXS)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.pop_up_text),
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.Normal,
                overflow = TextOverflow.Ellipsis,
                color = MaterialTheme.colorScheme.secondary,
                textAlign = TextAlign.Center,
            )

            Spacer(modifier = Modifier.height(dimensions.paddings.paddingXS))

            Button(
                modifier = Modifier
                    .padding(horizontal = dimensions.paddings.paddingM)
                    .height(dimensions.other.popUpButtonHeight)
                    .fillMaxWidth(),
                border = border,
                shape = shape,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent,
                ),
                onClick = { onButtonClicked() }
            ) {
                Text(
                    text = stringResource(R.string.pop_up_accept_button),
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.primary
                )
            }

            Spacer(modifier = Modifier.height(dimensions.paddings.paddingXS))
            
            Button(
                modifier = Modifier
                    .padding(horizontal = dimensions.paddings.paddingM)
                    .height(dimensions.other.popUpButtonHeight)
                    .fillMaxWidth(),
                border = border,
                shape = shape,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent,
                ),
                onClick = onDismissRequest
            ) {
                Text(
                    text = stringResource(R.string.pop_up_dismiss_button),
                    textAlign = TextAlign.Center,
                    color = ErrorColor
                )
            }
        }
    }
}
