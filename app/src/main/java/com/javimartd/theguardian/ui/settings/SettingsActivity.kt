package com.javimartd.theguardian.ui.settings

import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.Toolbar
import com.javimartd.theguardian.R
import com.javimartd.theguardian.databinding.ActivitySettingsBinding
import com.javimartd.theguardian.ui.common.BaseActivity
import com.javimartd.theguardian.ui.common.ToolbarManager
import com.javimartd.theguardian.ui.extensions.DelegatesExt

class SettingsActivity: BaseActivity(), ToolbarManager {

    companion object {
        const val OPTION1 = "option1"
        const val OPTION1_DEFAULT = false
    }

    private lateinit var binding: ActivitySettingsBinding

    private var nightMode: Boolean by DelegatesExt.preference(
        this,
        OPTION1,
        OPTION1_DEFAULT
    )

    override val toolbar: Toolbar by lazy {
        findViewById(R.id.toolbar)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)
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
            binding.textOptionNightMode.text = getString(R.string.option_night_mode_on)
        } else {
            binding.textOptionNightMode.text = getString(R.string.option_night_mode_off)
        }
        binding.switchDayNight.isChecked = nightMode
        binding.switchDayNight.setOnCheckedChangeListener { _, isChecked ->
            nightMode = binding.switchDayNight.isChecked
            when (isChecked) {
                true -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                false -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }
    }
}
