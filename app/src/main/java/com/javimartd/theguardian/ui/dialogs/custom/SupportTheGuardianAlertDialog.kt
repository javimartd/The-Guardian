package com.javimartd.theguardian.ui.dialogs.custom

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import com.javimartd.theguardian.R

class SupportTheGuardianAlertDialog(val context: Context): AlertDialogHelper() {

    override val alertDialogView: View by lazy {
        LayoutInflater.from(context).inflate(R.layout.dialog_support_the_guardian, null)
    }

    override val builder: AlertDialog.Builder = AlertDialog.Builder(context).setView(alertDialogView)

    private val subscribeButton: Button by lazy {
        alertDialogView.findViewById<Button>(R.id.materialButtonSubscribe)
    }

    private val contributeButton: Button by lazy {
        alertDialogView.findViewById<Button>(R.id.materialButtonContribute)
    }

    fun subscribeButtonClickListener(func: (() -> Unit)? = null) =
            with(subscribeButton) {
                setClickListenerToDialogButton(func)
            }

    fun contributeButtonClickListener(func: (() -> Unit)? = null) =
            with(contributeButton) {
                setClickListenerToDialogButton(func)
            }

    private fun View.setClickListenerToDialogButton(func: (() -> Unit)?) =
            setOnClickListener {
                func?.invoke()
                alertDialog?.dismiss()
            }
}