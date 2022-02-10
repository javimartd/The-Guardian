package com.javimartd.theguardian.ui.common

import android.content.Intent
import androidx.appcompat.graphics.drawable.DrawerArrowDrawable
import androidx.appcompat.widget.Toolbar
import com.javimartd.theguardian.R
import com.javimartd.theguardian.ui.extensions.ctx
import com.javimartd.theguardian.ui.settings.SettingsActivity

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
                R.id.action_settings -> {
                    toolbar.ctx.startActivity(Intent(toolbar.ctx, SettingsActivity::class.java))
                }
                R.id.action_share -> f()
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