package eka.com.researchgraph.View

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.TypedArray
import android.graphics.Canvas
import android.graphics.Color
import android.support.v4.view.ViewCompat
import android.util.AttributeSet
import android.view.View
import eka.com.researchgraph.*
import eka.com.researchgraph.Animator.PieChartAnimator
import eka.com.researchgraph.Renderer.PieChartRenderer

class PieChartView : View, PieChartDataProvider {
    var chartData = PieChartData()
    var animateType = PieChartAnimator.AnimateType.AnimateType1
        set(value) {
            animator.animateType = value
            field = value
        }
    private var animator = PieChartAnimator(this)
    private lateinit var chartRenderer: PieChartRenderer

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        getAttrs(attrs!!)
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        getAttrs(attrs!!, defStyleAttr)
    }

    fun setChartAnimateValue(value: Float, isAnimated: Boolean) {
        if (isAnimated) {
            chartRenderer.animateValue = value
        } else {
            chartRenderer.animateValue = when (animateType) {
                PieChartAnimator.AnimateType.AnimateType1 -> 1f
                PieChartAnimator.AnimateType.AnimateType2 -> 360f
            }
        }
        ViewCompat.postInvalidateOnAnimation(this)
    }

    fun cancelAnimation() {
        animator.cancelAnimation()
    }

    fun startAnimation() {
        animator.startAnimation()
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas) {
        chartRenderer.draw(canvas, animateType)
        super.onDraw(canvas)
    }

    override fun setPieChartData(chartData: PieChartData) {
        this.chartData = chartData
    }

    override fun getPieChartData(): PieChartData = chartData

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        setMeasuredDimension(widthMeasureSpec, (chartData.outerRadius * 2).toInt() + paddingTop + paddingBottom)
        chartRenderer = PieChartRenderer(this)
    }

    private fun getAttrs(attrs: AttributeSet, defStyleAttr: Int) {
        val typeArray: TypedArray = context.obtainStyledAttributes(attrs, R.styleable.CircleGraphView, defStyleAttr, 0)
        setTypeArray(typeArray)
    }

    private fun getAttrs(attrs: AttributeSet) {
        val typedArray: TypedArray = context.obtainStyledAttributes(attrs, R.styleable.CircleGraphView)
        setTypeArray(typedArray)
    }

    private fun setTypeArray(typedArray: TypedArray) {
        chartData.innerCircleColor = typedArray.getColor(R.styleable.CircleGraphView_innerCircleColor, Color.WHITE)

        chartData.innerRadius = typedArray.getDimension(R.styleable.CircleGraphView_innerCircleRadius, 0f)
        chartData.outerRadius = typedArray.getDimension(R.styleable.CircleGraphView_outerCircleRadius, chartData.innerRadius)

        chartData.innerStrokeColor = typedArray.getColor(R.styleable.CircleGraphView_innerStrokeColor, Color.WHITE)
        chartData.outerStrokeColor = typedArray.getColor(R.styleable.CircleGraphView_outerStrokeColor, Color.WHITE)

        chartData.innerStrokeWidth = typedArray.getDimension(R.styleable.CircleGraphView_innerStrokeWidth, 0f)
        chartData.outerStrokeWidth = typedArray.getDimension(R.styleable.CircleGraphView_outerStrokeWidth, 0f)

        typedArray.recycle()
    }
}
