package eka.com.researchgraph.Animator

import android.animation.Animator
import android.animation.ValueAnimator
import eka.com.researchgraph.Listener.ChartAnimationListener
import eka.com.researchgraph.View.BarChartView

/**
 * Created by eka on 2017. 11. 28..
 */
class BarChartAnimator(var barChart: BarChartView, var duration: Long) : ChartAnimator, ValueAnimator.AnimatorUpdateListener, Animator.AnimatorListener {
    var animator = ValueAnimator.ofFloat(0f, 1f)
    var animationListener = ChartAnimationListener.dummyAnimationListener

    constructor(barChart: BarChartView) : this(barChart, ChartAnimator.NORMAL_ANIMATION_DURATION)

    override fun startAnimation() {
        if (isAnimationStart())
            animator.cancel()
        animator.duration = duration
        animator.addUpdateListener(this)
        animator.addListener(this)
        animator.start()
    }

    override fun cancelAnimation() {
        animator.cancel()
    }

    override fun onAnimationUpdate(animator: ValueAnimator) {
        barChart.setChartAnimationValue(animator.animatedValue as Float, true)
    }

    override fun isAnimationStart(): Boolean = animator.isStarted

    override fun onAnimationRepeat(animator: Animator?) {
    }

    override fun onAnimationEnd(animator: Animator?) {
        animationListener.onAnimationFinished()
    }

    override fun onAnimationCancel(animator: Animator?) {
    }

    override fun onAnimationStart(animator: Animator?) {
        animationListener.onAnimationStarted()
    }
}