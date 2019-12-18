package com.javimartd.theguardian.ui.dialogs

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Window
import com.javimartd.theguardian.R
import javax.inject.Inject

class LoadingDialog @Inject constructor(): DialogActions {

    private lateinit var dialog: Dialog

    override fun createDialog(context: Context) {
        dialog = Dialog(context)
        dialog.window?.requestFeature(Window.FEATURE_NO_TITLE)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setContentView(R.layout.dialog_loading)
        dialog.setCancelable(false)
    }

    override fun showDialog() {
        if (!dialog.isShowing) {
            dialog.show()
        }
    }

    override fun hideDialog() {
        if (dialog.isShowing) {
            dialog.dismiss()
        }
    }
}