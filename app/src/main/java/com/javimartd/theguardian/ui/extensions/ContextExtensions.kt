package com.javimartd.theguardian.ui.extensions

import android.content.Context
import android.support.v4.content.ContextCompat

fun Context.color(res: Int): Int = ContextCompat.getColor(this, res)