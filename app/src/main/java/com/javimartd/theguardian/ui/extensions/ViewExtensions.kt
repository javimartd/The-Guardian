package com.javimartd.theguardian.ui.extensions

import android.app.Activity
import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import com.google.android.material.snackbar.Snackbar
import com.javimartd.theguardian.ui.dialogs.custom.SupportTheGuardianAlertDialog

// Adapter

fun ViewGroup.inflate(layoutRes: Int): View {
    return LayoutInflater.from(this.context).inflate(layoutRes, this, false)
}

// View

val View.ctx: Context
    get() = context

fun View.showSnack(snackBarText: String, timeLength: Int) {
    Snackbar.make(this, snackBarText, timeLength).show()
}

inline fun View.snack(message: String, length: Int = Snackbar.LENGTH_SHORT, f: Snackbar.() -> Unit) {
    val snack = Snackbar.make(this, message, length)
    snack.f()
    snack.show()
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

fun EditText.onTextChange(cb: (String) -> Unit) {
    this.addTextChangedListener(object: TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            cb(s.toString())
        }
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
    })
}

fun String.isValidPassword(): Boolean = this.isNotEmpty() && this.trim().length >= 6

// Dialog

inline fun Activity.showSupportTheGuardianAlertDialog(func: SupportTheGuardianAlertDialog.() -> Unit): AlertDialog =
        SupportTheGuardianAlertDialog(this).apply {
            func()
        }.create()
