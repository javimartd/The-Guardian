package com.javimartd.theguardian.ui.news

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.javimartd.theguardian.MyViewAction
import com.javimartd.theguardian.R
import com.javimartd.theguardian.TestTheGuardianApplication
import com.javimartd.theguardian.domain.model.News
import com.javimartd.theguardian.factory.NewsFactory
import com.javimartd.theguardian.ui.news.adapter.NewsAdapter
import com.javimartd.theguardian.ui.settings.SettingsActivity
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class NewsActivityTest {

    @Rule @JvmField
    val activity = ActivityTestRule<NewsActivity>(NewsActivity::class.java,
            false, false)

    @Test
    fun activityLaunches() {
        stubNewsRepository(Single.just(NewsFactory.makeNews(10)))
        activity.launchActivity(null)
    }

    @Test
    fun shouldBeToolbarVisible() {
        stubNewsRepository(Single.just(NewsFactory.makeNews(10)))
        activity.launchActivity(null)
        onView(withId(R.id.toolbar)).check(matches(isDisplayed()))
    }

    @Test
    fun shouldBeToolbarWithTitle() {
        stubNewsRepository(Single.just(NewsFactory.makeNews(10)))
        activity.launchActivity(null)
        onView(withId(R.id.toolbar)).check(matches(isDisplayed()))
    }

    @Test
    fun shouldBeActionSettingsButtonVisible() {
        stubNewsRepository(Single.just(NewsFactory.makeNews(10)))
        activity.launchActivity(null)
        onView(withId(R.id.action_settings)).check(matches(isDisplayed()))
    }

    @Test
    fun shouldRecyclerViewVisible() {
        stubNewsRepository(Single.just(NewsFactory.makeNews(10)))
        activity.launchActivity(null)
        onView(withId(R.id.recycler)).check(matches(isDisplayed()))
    }

    @Test
    fun recyclerScrollToPosition() {
        stubNewsRepository(Single.just(NewsFactory.makeNews(10)))
        activity.launchActivity(null)
        onView(ViewMatchers.withId(R.id.recycler)).perform(RecyclerViewActions.
                actionOnItemAtPosition<NewsAdapter.ViewHolder>(5, ViewActions.click()))
    }

    @Test
    fun recyclerScrollToPositionAndClickOnItemButton() {
        stubNewsRepository(Single.just(NewsFactory.makeNews(10)))
        activity.launchActivity(null)
        onView(withId(R.id.recycler))
                .perform(RecyclerViewActions.actionOnItemAtPosition<NewsAdapter.ViewHolder>(2,
                                MyViewAction.clickChildViewWithId(R.id.buttonReadMore)))
    }

    @Test
    fun checkSettingsActivityIsAvailable() {
        stubNewsRepository(Single.just(NewsFactory.makeNews(10)))
        activity.launchActivity(null)
        Intents.init()
        onView(withId(R.id.action_settings)).perform(ViewActions.click())
        intended(hasComponent(SettingsActivity::class.java.name))
    }

    private fun stubNewsRepository(single: Single<List<News>>) {
        whenever(TestTheGuardianApplication.instance.component.getNewsRepository().getNews())
                .thenReturn(single)
    }
}