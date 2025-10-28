package com.ortin.notifications

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.ortin.notifications.presentation.detail.DetailScreen
import com.ortin.notifications.ui.theme.NotificationsTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            // TODO: replace with navigation in the future
            NotificationsTheme {
                Scaffold { paddingValues ->
                    DetailScreen(modifier = Modifier.padding(paddingValues))
                }
            }
        }
    }
}
