package com.javimartd.theguardian.ui.common

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.javimartd.theguardian.ui.utils.ActivityTransition
import dagger.android.AndroidInjection

abstract class BaseActivity: AppCompatActivity() {

    companion object {
        const val KEY_TRANSITION = "key_transition"
    }

    private var activityTransition: ActivityTransition? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        setUpTransitionFromBundle()
        initializeEnterTransition()
    }

    override fun onPause() {
        super.onPause()
        initializeExitTransition()
    }

    /**************
     * transitions
     *************/

    private fun setUpTransitionFromBundle() {
        intent?.let {
            if (intent.hasExtra(KEY_TRANSITION)) {
                activityTransition = it.extras?.getSerializable(KEY_TRANSITION) as ActivityTransition
            }
        }
    }

    private fun setAnimationTransition(enter: Int, exit: Int) {
        overridePendingTransition(enter, exit)
    }

    private fun initializeEnterTransition() {
        activityTransition?.let {
            setAnimationTransition(it.initEnter, it.initExit)
        }
    }

    private fun initializeExitTransition() {
        activityTransition?.let {
            setAnimationTransition(it.endEnter, it.endExit)
        }
    }
}