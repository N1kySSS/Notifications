package com.ortin.notifications.ui.theme

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

internal val LocalDimensions = compositionLocalOf { Dimensions() }

internal data class Dimensions(
    val paddings: Paddings = Paddings(),
    val corners: CornerDimensions = CornerDimensions(),
    val iconDimensions: IconDimensions = IconDimensions(),
    val other: OtherDimensions = OtherDimensions(),
    val border: BorderDimensions = BorderDimensions()
)

internal data class Paddings(
    val paddingXXS: Dp = 4.dp,
    val paddingXS: Dp = 8.dp,
    val paddingS: Dp = 16.dp,
    val paddingM: Dp = 24.dp,
    val paddingL: Dp = 32.dp,
    val paddingXL: Dp = 48.dp
)

internal data class IconDimensions(
    val sizeS: Dp = 16.dp,
    val sizeM: Dp = 24.dp
)

internal data class CornerDimensions(
    val cornerM: Dp = 12.dp,
    val cornerXL: Dp = 20.dp,
)

internal data class OtherDimensions(
    val popUpButtonHeight: Dp = 40.dp,
    val popUpHeight: Dp = 180.dp,
    val componentMinHeight: Dp = 56.dp
)

internal data class BorderDimensions(
    val popupBorderWidth: Dp =  1.dp,
)
