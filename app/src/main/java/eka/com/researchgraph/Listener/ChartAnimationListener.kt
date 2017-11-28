package eka.com.researchgraph.Listener

/**
 * Created by eka on 2017. 11. 28..
 */
interface ChartAnimationListener{
    fun onAnimationStarted()
    fun onAnimationFinished()
    companion object {
        var dummyAnimationListener = object : ChartAnimationListener {
            override fun onAnimationStarted() {
            }

            override fun onAnimationFinished() {
            }
        }
    }
}