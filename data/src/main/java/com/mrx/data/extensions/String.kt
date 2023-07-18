package com.mrx.data.extensions

fun String?.getValueOrEmpty(): String {
    return when (this) {
        null -> ""
        else -> this
    }
}