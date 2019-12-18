package com.javimartd.theguardian.ui.extensions

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

const val FORMAT_DATE_TIME_API = "yyyy-MM-dd'T'HH:mm:ss'Z'"

fun Long.toDateString(dateFormat: Int = DateFormat.MEDIUM): String {
    val df = DateFormat.getDateInstance(dateFormat, Locale.getDefault())
    return df.format(this)
}

fun String.toLong(format: String): Long {
    val f =  SimpleDateFormat(format)
    val d = f.parse(this)
    return d.time
}