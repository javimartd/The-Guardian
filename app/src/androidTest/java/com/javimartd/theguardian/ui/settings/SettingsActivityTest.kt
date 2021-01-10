package com.javimartd.theguardian.ui.settings

import android.content.Context
import android.content.SharedPreferences
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.javimartd.theguardian.R
import com.javimartd.theguardian.TestTheGuardianApplication
import com.javimartd.theguardian.ui.extensions.DelegatesExt
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class SettingsActivityTest {

    private lateinit var sharedPreferences: SharedPreferences
    private var dayNightOption: Boolean by DelegatesExt.preference(
            TestTheGuardianApplication.instance,
            SettingsActivity.OPTION1,
            SettingsActivity.OPTION1_DEFAULT)

    private val stringNightModeOn = TestTheGuardianApplication.instance.resources.getString(R.string.option_night_mode_on)
    private val stringNightModeOff = TestTheGuardianApplication.instance.resources.getString(R.string.option_night_mode_off)

    @Before
    fun setUp() {
        sharedPreferences = TestTheGuardianApplication.instance.getSharedPreferences("default", Context.MODE_PRIVATE)
    }

    @Test
    fun settingsActivityLaunchesSuccessfully() {
       ActivityScenario.launch(SettingsActivity::class.java)
    }

    @Test
    fun shouldNightModeTextAndSwitchVisible() {
        ActivityScenario.launch(SettingsActivity::class.java)
        Espresso.onView(ViewMatchers.withId(R.id.textOptionNightMode))
                .check(ViewAssertions.matches(isDisplayed()))

        Espresso.onView(ViewMatchers.withId(R.id.switchDayNight))
                .check(ViewAssertions.matches(isDisplayed()))
    }

    @Test
    fun nightModeTextIsCorrectWithSwitchOn() {
        dayNightOption = true
        ActivityScenario.launch(SettingsActivity::class.java)
        Espresso.onView(ViewMatchers.withId(R.id.textOptionNightMode))
                .check(ViewAssertions.matches(withText(stringNightModeOn)))

    }

    @Test
    fun nightModeTextIsCorrectWithSwitchOff() {
        dayNightOption = false
        ActivityScenario.launch(SettingsActivity::class.java)
        Espresso.onView(ViewMatchers.withId(R.id.textOptionNightMode))
                .check(ViewAssertions.matches(withText(stringNightModeOff)))

    }

    @Test
    fun nightModeTextChangesCorrectlyWhenSwitchIsClicked() {
        dayNightOption = false
        ActivityScenario.launch(SettingsActivity::class.java)
        Espresso.onView(ViewMatchers.withId(R.id.switchDayNight))
                .perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.textOptionNightMode))
                .check(ViewAssertions.matches(withText(stringNightModeOn)))
        Espresso.onView(ViewMatchers.withId(R.id.switchDayNight))
                .perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.textOptionNightMode))
                .check(ViewAssertions.matches(withText(stringNightModeOff)))
    }
}