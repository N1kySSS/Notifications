package com.ortin.notifications.core

import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

internal fun Long.toFormattedDateTime(): String {
    val formatter = DateTimeFormatter.ofPattern(DateTimePatterns.READABLE_DATE_PATTERN)
        .withZone(ZoneId.systemDefault())
    return formatter.format(Instant.ofEpochMilli(this))
}
