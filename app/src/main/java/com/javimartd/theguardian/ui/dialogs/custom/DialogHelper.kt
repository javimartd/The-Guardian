package com.javimartd.theguardian.ui.dialogs.custom

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.view.Window

abstract class DialogHelper(val context: Context) {

    abstract val dialog: Dialog
    abstract val dialogView: View

    open var cancelable: Boolean = true

    open fun create(): Dialog {
        dialog.setCancelable(cancelable)
        dialog.window?.requestFeature(Window.FEATURE_NO_TITLE)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setContentView(dialogView)
        return dialog
    }

    open fun onCancelListener(func: () -> Unit) {
        dialog.setOnCancelListener { func() }
    }
}