package eka.com.researchgraph.Animator

import eka.com.researchgraph.Listener.ChartAnimationListener

/**
 * Created by eka on 2017. 11. 28..
 */
interface ChartAnimator {
    fun startAnimation()
    fun cancelAnimation()
    fun isAnimationStart(): Boolean

    companion object {
        val FAST_ANIMATION_DURAITON = 500L
        val NORMAL_ANIMATION_DURATION = 1000L
        val SLOW_ANIMATION_DURATION = 2000L
    }
}