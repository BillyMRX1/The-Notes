package com.mrx.data.extensions

import java.text.SimpleDateFormat
import java.util.Locale

fun Long?.toStringFormat(): String {
    if (this == null) return ""
    val timeTypeSecond = 1.0
    val timeFormat = "dd MMM yyyy, HH:mm"
    val currentTime = (this * timeTypeSecond).toLong()
    val dateFormat = SimpleDateFormat(timeFormat, Locale.US)

    return dateFormat.format(currentTime)
}