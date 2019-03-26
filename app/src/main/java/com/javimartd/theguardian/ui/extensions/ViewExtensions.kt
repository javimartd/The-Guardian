package com.javimartd.theguardian.ui.extensions

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.google.android.material.snackbar.Snackbar

// Adapter

fun ViewGroup.inflate(layoutRes: Int): View {
    return LayoutInflater.from(this.context).inflate(layoutRes, this, false)
}

// View

val View.ctx: Context
    get() = context

fun ImageView.loadUrl(url: String) {
    Glide.with(context).load(url).transition(DrawableTransitionOptions.withCrossFade()).into(this)
}

fun View.showSnackbar(snackBarText: String, timeLength: Int) {
    Snackbar.make(this, snackBarText, timeLength).show()
}

fun View.hide() : View {
    if (visibility != View.INVISIBLE) {
        visibility = View.INVISIBLE
    }
    return this
}

fun View.show() : View {
    if (visibility != View.VISIBLE) {
        visibility = View.VISIBLE
    }
    return this
}

fun View.remove() : View {
    if (visibility != View.GONE) {
        visibility = View.GONE
    }
    return this
}

fun View.toggleVisibility() : View {
    visibility = if (visibility == View.VISIBLE) {
        View.GONE
    } else {
        View.VISIBLE
    }
    return this
}