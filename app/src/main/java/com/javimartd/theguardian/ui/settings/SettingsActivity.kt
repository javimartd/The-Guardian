package com.javimartd.theguardian.ui.settings

import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.Toolbar
import com.javimartd.theguardian.R
import com.javimartd.theguardian.ui.common.BaseActivity
import com.javimartd.theguardian.ui.common.ToolbarManager
import com.javimartd.theguardian.ui.extensions.DelegatesExt
import kotlinx.android.synthetic.main.activity_settings.*
import org.jetbrains.anko.find

class SettingsActivity: BaseActivity(), ToolbarManager {

    companion object {
        const val OPTION1 = "option1"
        const val OPTION1_DEFAULT = false
    }

    override val toolbar by lazy { find<Toolbar>(R.id.toolbar) }

    private var nightMode: Boolean by DelegatesExt.preference(this, OPTION1, OPTION1_DEFAULT)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        setUpUI()
    }

    private fun setUpUI() {
        setUpToolbar()
        setUpSwitchDayNightTheme()
    }

    private fun setUpToolbar() {
        toolbarTitle = getString(R.string.toolbar_activity_settings)
        enableHomeAsUp{ onBackPressed() }
    }

    private fun setUpSwitchDayNightTheme() {
        if (nightMode) {
            textOptionNightMode.text = getString(R.string.option_night_mode_on)
        } else {
            textOptionNightMode.text = getString(R.string.option_night_mode_off)
        }
        switchDayNight.isChecked = nightMode
        switchDayNight.setOnCheckedChangeListener { _, isChecked ->
            nightMode = switchDayNight.isChecked
            when (isChecked) {
                true -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                false -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }
    }
}
