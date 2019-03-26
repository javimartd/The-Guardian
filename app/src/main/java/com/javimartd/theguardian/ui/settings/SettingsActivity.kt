package com.javimartd.theguardian.ui.settings

import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.javimartd.theguardian.R
import com.javimartd.theguardian.ui.base.ToolbarManager
import com.javimartd.theguardian.ui.common.BaseActivity
import com.javimartd.theguardian.ui.extensions.DelegatesExt
import kotlinx.android.synthetic.main.activity_settings.*
import org.jetbrains.anko.find

class SettingsActivity : BaseActivity(), ToolbarManager {

    companion object {
        const val OPTION1 = "option1"
        const val OPTION1_DEFAULT = false
    }

    override val toolbar by lazy { find<Toolbar>(R.id.toolbar) }

    private var option1: Boolean by DelegatesExt.preference(this, OPTION1, OPTION1_DEFAULT)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        setUpUI()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        option1 = switchOption1.isChecked
    }

    /********************
     * private functions
     ********************/

    private fun setUpUI() {
        setUpToolbar()
        setUpOptions()
    }

    private fun setUpOptions() {
        switchOption1.isChecked = option1
    }

    private fun setUpToolbar() {
        toolbarTitle = getString(R.string.toolbar_activity_settings)
        enableHomeAsUp{ onBackPressed() }
    }
}
