package com.javimartd.theguardian.ui.settings

import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.javimartd.theguardian.R
import com.javimartd.theguardian.ui.base.ToolbarManager
import com.javimartd.theguardian.ui.common.BaseActivity
import com.javimartd.theguardian.ui.extensions.DelegatesExt
import kotlinx.android.synthetic.main.activity_settings.*
import org.jetbrains.anko.find
import javax.inject.Inject

class SettingsActivity: BaseActivity<SettingsContract.View, SettingsContract.Presenter>(),
        ToolbarManager, SettingsContract.View {

    companion object {
        const val OPTION1 = "option1"
        const val OPTION1_DEFAULT = false
    }

    @Inject lateinit var presenter: SettingsPresenter

    override val toolbar by lazy { find<Toolbar>(R.id.toolbar) }

    private var dayNightOption: Boolean by DelegatesExt.preference(this, OPTION1, OPTION1_DEFAULT)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        setUpPresenter()
        setUpUI()
    }

    override fun onDestroy() {
        presenter.detachView()
        super.onDestroy()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        dayNightOption = switchDayNight.isChecked
    }

    override fun setOptionNameDayNight(name: String) {
        textOptionDayNight.text = name
    }

    private fun setUpUI() {
        setUpToolbar()
        setUpDayNightOption()
    }

    private fun setUpPresenter() {
        presenter.attachView(this)
    }

    private fun setUpDayNightOption() {
        switchDayNight.setOnCheckedChangeListener { _, isChecked -> presenter.initializeDayNightStatus(isChecked) }
        switchDayNight.isChecked = dayNightOption
    }

    private fun setUpToolbar() {
        toolbarTitle = getString(R.string.toolbar_activity_settings)
        enableHomeAsUp{ onBackPressed() }
    }
}
