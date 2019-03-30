package com.javimartd.theguardian.ui.navigator

import android.content.Context
import android.content.Intent
import com.javimartd.theguardian.ui.common.BaseActivity
import com.javimartd.theguardian.ui.utils.ActivityTransition
import javax.inject.Inject

class Navigator @Inject constructor() {


    private fun startActivityWithAnimation(context: Context, intent: Intent, activityTransition: ActivityTransition) {
        setIntentAnimation(intent, activityTransition)
        context.startActivity(intent)
    }

    private fun setIntentAnimation(intent: Intent, activityTransition: ActivityTransition) {
        intent.putExtra(BaseActivity.KEY_TRANSITION, activityTransition)
    }
}