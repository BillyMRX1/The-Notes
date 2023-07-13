package com.mrx.data.extensions

fun Int?.getValueOrZero(): Int {
    return this ?: 0
}