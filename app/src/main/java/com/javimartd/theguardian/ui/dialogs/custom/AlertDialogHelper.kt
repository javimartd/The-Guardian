package com.javimartd.theguardian.ui.dialogs.custom

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import androidx.appcompat.app.AlertDialog

abstract class AlertDialogHelper {

    abstract val alertDialogView: View
    abstract val builder: AlertDialog.Builder

    open var cancelable: Boolean = true
    open var isBackGroundTransparent: Boolean = true

    open var alertDialog: AlertDialog? = null

    open fun create(): AlertDialog {
        alertDialog = builder
                .setCancelable(cancelable)
                .create()

        if (isBackGroundTransparent)
            alertDialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        return alertDialog!!
    }

    open fun onCancelListener(func: () -> Unit): AlertDialog.Builder? =
            builder.setOnCancelListener {
                func()
            }
}