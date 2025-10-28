package com.ortin.notifications.ui.components.cards

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import com.ortin.notifications.R
import com.ortin.notifications.core.LightModePreview
import com.ortin.notifications.ui.components.niceRippleClickable
import com.ortin.notifications.ui.components.noRippleClickable
import com.ortin.notifications.ui.components.spacers.VerticalSpacer
import com.ortin.notifications.ui.theme.LocalDimensions
import com.ortin.notifications.ui.theme.NotificationsTheme
import com.ortin.notifications.ui.utils.Constants
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

@Composable
internal fun NotificationCard(
    date: String,
    title: String,
    description: String,
    isRead: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val dimensions = LocalDimensions.current
    val cornerShape = RoundedCornerShape(dimensions.corners.cornerL)

    var expanded by rememberSaveable { mutableStateOf(false) }
    var showImage by rememberSaveable { mutableStateOf(false) }

    val backgroundColor = if (isRead) {
        MaterialTheme.colorScheme.surfaceVariant
    } else {
        MaterialTheme.colorScheme.primaryContainer
    }

    val titleColor = if (isRead) {
        MaterialTheme.colorScheme.onSurfaceVariant
    } else {
        MaterialTheme.colorScheme.onPrimaryContainer
    }

    val descriptionColor = if (isRead) {
        MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.8f)
    } else {
        MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.9f)
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .animateContentSize()
            .background(backgroundColor, cornerShape)
            .clip(cornerShape)
            .niceRippleClickable { onClick() }
            .padding(dimensions.paddings.paddingM)
    ) {
        Text(
            text = date,
            style = MaterialTheme.typography.labelSmall,
            color = titleColor,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        VerticalSpacer(dimensions.paddings.paddingXS)

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .animateContentSize(),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold),
                color = titleColor,
                maxLines = if (expanded) {
                    Int.MAX_VALUE
                } else {
                    Constants.DEFAULT_COLLAPSED_TITLE_COUNTS
                },
                overflow = TextOverflow.Ellipsis
            )

            VerticalSpacer(dimensions.paddings.paddingXS)

            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium,
                color = descriptionColor,
                textAlign = TextAlign.Start,
                maxLines = if (expanded) {
                    Int.MAX_VALUE
                } else {
                    Constants.DEFAULT_COLLAPSED_DESCRIPTION_COUNTS
                },
                overflow = TextOverflow.Ellipsis,
                onTextLayout = { result ->
                    showImage = result.hasVisualOverflow || result.lineCount > Constants.DEFAULT_COLLAPSED_DESCRIPTION_COUNTS
                }
            )

            if (showImage) {
                VerticalSpacer(dimensions.paddings.paddingXS)

                Image(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .noRippleClickable { expanded = !expanded },
                    painter = painterResource(
                        if (expanded) {
                            R.drawable.icon_arrow_up_expandable_text
                        } else {
                            R.drawable.icon_arrow_down_expandable_text
                        }
                    ),
                    contentDescription = null
                )
            }
        }
    }
}

@LightModePreview
@Preview
@Composable
private fun NotificationCardPreview() {
    val formatter = DateTimeFormatter.ofPattern(Constants.READABLE_DATE_PATTERN, Locale.getDefault())
    val date = LocalDateTime.now().format(formatter)
    NotificationsTheme {
        Surface {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .statusBarsPadding()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                NotificationCard(date = date, title = LoremIpsum(5).values.first(), description = LoremIpsum(10).values.first(), isRead = false, onClick = {})
                NotificationCard(date = date, title = LoremIpsum(10).values.first(), description = LoremIpsum(20).values.first(), isRead = true, onClick = {})
                NotificationCard(date = date, title = LoremIpsum(5).values.first(), description = LoremIpsum(30).values.first(), isRead = true, onClick = {})
                NotificationCard(date = date, title = LoremIpsum(15).values.first(), description = LoremIpsum(40).values.first(), isRead = false, onClick = {})
                NotificationCard(date = date, title = LoremIpsum(5).values.first(), description = LoremIpsum(50).values.first(), isRead = false, onClick = {})
                NotificationCard(date = date, title = LoremIpsum(20).values.first(), description = LoremIpsum(60).values.first(), isRead = true, onClick = {})
                NotificationCard(date = date, title = LoremIpsum(5).values.first(), description = LoremIpsum(70).values.first(), isRead = true, onClick = {})
            }
        }
    }
}
