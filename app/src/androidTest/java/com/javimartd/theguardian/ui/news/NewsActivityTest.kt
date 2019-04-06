package com.javimartd.theguardian.ui.news

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.UiController
import android.support.test.espresso.ViewAction
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.intent.Intents
import android.support.test.espresso.intent.Intents.intended
import android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.view.View
import com.javimartd.theguardian.R
import com.javimartd.theguardian.ui.settings.SettingsActivity
import org.hamcrest.Matcher
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class NewsActivityTest {

    @get:Rule
    var activityRule: ActivityTestRule<NewsActivity> = ActivityTestRule(NewsActivity::class.java)

    @Before
    fun setUp() {
        Thread.sleep(2500)
    }

    @Test
    fun shouldBeToolbar() {
        Assert.assertNotNull(activityRule.activity.toolbar)
    }

    @Test
    fun shouldBeToolbarVisible() {
        onView(withId(R.id.toolbar)).check(matches(isDisplayed()))
    }

    @Test
    fun shouldBeToolbarWithTitle() {
        onView(withId(R.id.toolbar)).check(matches(isDisplayed()))
    }

    @Test
    fun shouldBeActionSettingsButtonVisible() {
        onView(withId(R.id.action_settings)).check(matches(isDisplayed()))
    }

    @Test
    fun shouldRecyclerViewVisible() {
        onView(withId(R.id.recycler)).check(matches(isDisplayed()))
    }

    @Test
    fun recyclerScrollToPosition() {
        onView(ViewMatchers.withId(R.id.recycler)).perform(RecyclerViewActions.
                actionOnItemAtPosition<NewsAdapter.ViewHolder>(2, ViewActions.click()))
    }

    @Test
    fun recyclerScrollToPositionAndClickOnItemButton() {
        onView(withId(R.id.recycler))
                .perform(RecyclerViewActions
                        .actionOnItemAtPosition<NewsAdapter.ViewHolder>(
                                2,
                                MyViewAction.clickChildViewWithId(R.id.buttonReadMore)))
    }

    @Test
    fun checkSettingsActivityIsAvailable() {
        Intents.init()
        onView(withId(R.id.action_settings)).perform(ViewActions.click())
        intended(hasComponent(SettingsActivity::class.java.name))
    }

    object MyViewAction {

        fun clickChildViewWithId(id: Int): ViewAction {
            return object : ViewAction {
                override fun getConstraints(): Matcher<View>? {
                    return null
                }

                override fun getDescription(): String {
                    return "Click on a child view with specified id."
                }

                override fun perform(uiController: UiController, view: View) {
                    val v = view.findViewById<View>(id)
                    v.performClick()
                }
            }
        }

    }

}