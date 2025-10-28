package com.ortin.notifications.presentation.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.ortin.notifications.R
import com.ortin.notifications.ui.theme.LocalDimensions
import org.koin.androidx.compose.koinViewModel

@Composable
fun DetailScreen(modifier: Modifier = Modifier) {
    val dimensions = LocalDimensions.current
    val scrollState = rememberScrollState()

    val detailViewModel: DetailScreenViewModel = koinViewModel()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = dimensions.paddings.paddingXS)
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = dimensions.paddings.paddingS)
        ) {
            IconButton(
                modifier = Modifier
                    .size(40.dp)
                    .align(Alignment.CenterStart),
                onClick = { /* TODO - navigation on the history screen */ }
            ) {
                Icon(
                    modifier = Modifier.size(24.dp),
                    imageVector = Icons.Default.ArrowBackIosNew,
                    contentDescription = stringResource(R.string.detail_screen_go_back_icon),
                    tint = MaterialTheme.colorScheme.primary
                )
            }
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = detailViewModel.title.value,
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
        }
        Text(
            text = detailViewModel.description.value,
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Normal,
            color = MaterialTheme.colorScheme.secondary
        )
        AsyncImage(
            modifier = Modifier
                .clip(RoundedCornerShape(dimensions.corners.cornerM))
                .padding(vertical = dimensions.paddings.paddingS),
            model = detailViewModel.image.value,
            contentDescription = stringResource(R.string.detail_screen_image_description),
            error = painterResource(R.drawable.im_default),
            placeholder = painterResource(R.drawable.im_default),
            fallback = painterResource(R.drawable.im_default),
        )
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(dimensions.other.componentMinMinHeight),
            onClick = detailViewModel::reportAction,
            enabled = true,
            shape = RoundedCornerShape(dimensions.corners.cornerM)
        ) {
            Text(
                text = stringResource(R.string.detail_screen_button),
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}
