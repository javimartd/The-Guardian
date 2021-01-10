package com.javimartd.theguardian.ui.common

import androidx.appcompat.graphics.drawable.DrawerArrowDrawable
import androidx.appcompat.widget.Toolbar
import com.javimartd.theguardian.R
import com.javimartd.theguardian.TheGuardianApplication
import com.javimartd.theguardian.ui.extensions.ctx
import com.javimartd.theguardian.ui.settings.SettingsActivity
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

interface ToolbarManager {

    val toolbar: Toolbar

    var toolbarTitle: String
        get() = toolbar.title.toString()
        set(value) {
            toolbar.title = value
        }

    fun enableHomeAsUp(up: () -> Unit) {
        toolbar.navigationIcon = createUpDrawable()
        toolbar.setNavigationOnClickListener { up() }
    }

    fun initializeToolbar(f: () -> Unit = {}) {
        toolbar.inflateMenu(R.menu.toolbar_menu)
        toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.action_settings -> toolbar.ctx.startActivity<SettingsActivity>()
                R.id.action_share -> f()
                else -> TheGuardianApplication.instance.toast("Unknown option")
            }
            true
        }
    }

    fun setShareButtonToVisible() {
        toolbar.menu.findItem(R.id.action_share).isVisible = true
    }

    fun setSettingsButtonToInvisible() {
        toolbar.menu.findItem(R.id.action_settings).isVisible = false
    }

    private fun createUpDrawable() = DrawerArrowDrawable(toolbar.ctx).apply { progress = 1f }
}