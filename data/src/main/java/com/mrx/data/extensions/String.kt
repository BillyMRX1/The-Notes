package com.mrx.data.extensions

import com.google.gson.Gson

fun String?.getValueOrEmpty(): String {
    return when (this) {
        null -> ""
        else -> this
    }
}

fun <A> String.fromJson(type: Class<A>): A {
    return Gson().fromJson(this, type)
}

fun <A> A.toJson(): String? {
    return Gson().toJson(this)
}