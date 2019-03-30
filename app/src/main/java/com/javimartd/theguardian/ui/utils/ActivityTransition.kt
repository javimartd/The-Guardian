package com.javimartd.theguardian.ui.utils

import com.javimartd.theguardian.R
import java.io.Serializable

enum class ActivityTransition(val initEnter: Int,
                              val initExit: Int,
                              val endEnter: Int,
                              val endExit: Int): Serializable {
    MODAL (
            R.anim.anim_slide_up,
            R.anim.anim_no_change,
            R.anim.anim_no_change,
            R.anim.anim_slide_down),

    BROTHER(
            R.anim.anim_slide_enter_from_right,
            R.anim.anim_slide_exit_to_left,
            R.anim.anim_slide_enter_from_left,
            R.anim.anim_slide_exit_to_right);

}