package com.javimartd.theguardian.news

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.javimartd.theguardian.R
import com.javimartd.theguardian.ui.news.NewsActivity
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NewsActivityTest {

    @get:Rule
    var activityRule: ActivityTestRule<NewsActivity> = ActivityTestRule(NewsActivity::class.java)

    @Test
    fun shouldBeToolbar() {
        val toolbar = activityRule.activity.toolbar
        Assert.assertNotNull(toolbar)
    }

    @Test
    fun shouldBeToolbarVisible() {
        onView(withId(R.id.toolbar)).check(matches(isDisplayed()))
    }

    @Test
    fun shouldBeToolbarWithTitle() {
        val toolbarId = activityRule.activity.toolbar.id
        val toolbarTitle = onView(withText(R.string.app_name))
        toolbarTitle.check(matches(withParent(withId(toolbarId))))
    }

    @Test
    fun shouldBeActionSettingsButtonVisible() {
        onView(withId(R.id.action_settings)).check(matches(isDisplayed()))
    }

    @Test
    fun shouldRecyclerViewVisible() {
        onView(withId(R.id.recycler)).check(matches(isDisplayed()))
    }
}