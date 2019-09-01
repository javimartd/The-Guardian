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
import com.javimartd.theguardian.TheGuardianApplication
import com.javimartd.theguardian.ui.extensions.DelegatesExt
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class SettingsActivityTest {

    lateinit var sharedPreferences: SharedPreferences
    var dayNightOption: Boolean by DelegatesExt.preference(
            TheGuardianApplication.instance,
            SettingsActivity.OPTION1,
            SettingsActivity.OPTION1_DEFAULT)

    private val stringNightModeOn = TheGuardianApplication.instance.resources.getString(R.string.option_night_mode_on)
    private val stringNightModeOff = TheGuardianApplication.instance.resources.getString(R.string.option_night_mode_off)

    @Before
    fun setUp() {
        sharedPreferences = TheGuardianApplication.instance.getSharedPreferences("default", Context.MODE_PRIVATE)
    }

    @Test
    fun newsActivityLaunchesSuccessfully() {
       ActivityScenario.launch(SettingsActivity::class.java)
    }

    @Test
    fun shouldDayNightTextAndSwitchVisible() {
        ActivityScenario.launch(SettingsActivity::class.java)
        Espresso.onView(ViewMatchers.withId(R.id.textOptionDayNight))
                .check(ViewAssertions.matches(isDisplayed()))

        Espresso.onView(ViewMatchers.withId(R.id.switchDayNight))
                .check(ViewAssertions.matches(isDisplayed()))
    }

    @Test
    fun dayNightTextIsCorrectWithSwitchOn() {
        dayNightOption = true
        ActivityScenario.launch(SettingsActivity::class.java)
        Espresso.onView(ViewMatchers.withId(R.id.textOptionDayNight))
                .check(ViewAssertions.matches(withText(stringNightModeOn)))

    }

    @Test
    fun dayNightTextIsCorrectWithSwitchOff() {
        dayNightOption = false
        ActivityScenario.launch(SettingsActivity::class.java)
        Espresso.onView(ViewMatchers.withId(R.id.textOptionDayNight))
                .check(ViewAssertions.matches(withText(stringNightModeOff)))

    }

    @Test
    fun dayNightTextChangesCorrectlyWhenSwitchIsClicked() {
        dayNightOption = false
        ActivityScenario.launch(SettingsActivity::class.java)
        Espresso.onView(ViewMatchers.withId(R.id.switchDayNight))
                .perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.textOptionDayNight))
                .check(ViewAssertions.matches(withText(stringNightModeOn)))
        Espresso.onView(ViewMatchers.withId(R.id.switchDayNight))
                .perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.textOptionDayNight))
                .check(ViewAssertions.matches(withText(stringNightModeOff)))
    }
}