package com.javimartd.theguardian.ui.dialogs

import android.content.Context

interface DialogActions {
    fun createDialog(context: Context)
    fun showDialog()
    fun hideDialog()
}