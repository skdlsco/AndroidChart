package eka.com.researchgraph.Animator

import android.animation.Animator
import android.animation.ValueAnimator
import eka.com.researchgraph.Listener.ChartAnimationListener
import eka.com.researchgraph.View.PieChartView

/**
 * Created by eka on 2017. 11. 27..
 */
class PieChartAnimator(private var pieChart: PieChartView, var duration: Long) : ChartAnimator, Animator.AnimatorListener, ValueAnimator.AnimatorUpdateListener {


    var animator = ValueAnimator()
    var animationListener: ChartAnimationListener = ChartAnimationListener.dummyAnimationListener
    var animateType = AnimateType.AnimateType1

    constructor(pieChart: PieChartView) : this(pieChart, ChartAnimator.NORMAL_ANIMATION_DURATION)

    override fun startAnimation() {
        if (isAnimationStart())
            animator.cancel()
        animator = when (animateType) {
            AnimateType.AnimateType1 -> {
                ValueAnimator.ofFloat(0f, 1f)
            }
            AnimateType.AnimateType2 -> {
                ValueAnimator.ofFloat(0f, 360f)
            }
        }
        animator.duration = duration
        animator.addUpdateListener(this)
        animator.addListener(this)
        animator.start()
    }

    override fun cancelAnimation() {
        animator.cancel()
    }

    override fun onAnimationUpdate(animator: ValueAnimator) {
        pieChart.setChartAnimateValue(animator.animatedValue as Float, true)
    }

    override fun isAnimationStart(): Boolean = animator.isStarted


    override fun onAnimationStart(animator: Animator?) {
        pieChart.setChartAnimateValue(0f, true)
        animationListener.onAnimationStarted()
    }

    override fun onAnimationEnd(animator: Animator?) {
        pieChart.setChartAnimateValue(0f, false)
        animationListener.onAnimationFinished()
    }

    override fun onAnimationCancel(animator: Animator?) {
    }

    override fun onAnimationRepeat(animator: Animator?) {
    }

    enum class AnimateType {
        AnimateType1, AnimateType2
    }
}