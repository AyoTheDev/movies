package com.ayo.movies.utils

import android.view.View
import android.view.animation.AlphaAnimation

object AnimationUtils {

    fun setFadeAnimation(view: View, duration: Long) {
        val anim = AlphaAnimation(0.0f, 1.0f)
        anim.duration = duration
        view.startAnimation(anim)
    }
}