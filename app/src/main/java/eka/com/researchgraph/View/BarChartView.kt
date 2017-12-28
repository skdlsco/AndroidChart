package eka.com.researchgraph.View

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.TypedArray
import android.graphics.*
import android.support.v4.view.ViewCompat
import android.util.AttributeSet
import android.view.View
import eka.com.researchgraph.Animator.BarChartAnimator
import eka.com.researchgraph.BarChartDataProvider
import eka.com.researchgraph.Data.BarChartData
import eka.com.researchgraph.R
import eka.com.researchgraph.Renderer.BarChartRenderer

class BarChartView : View, BarChartDataProvider {


    var chartData = BarChartData()
    var animator = BarChartAnimator(this)
    lateinit var chartRenderer: BarChartRenderer

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        getAttrs(attrs!!)
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        getAttrs(attrs!!, defStyleAttr)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        setMeasuredDimension((paddingStart + paddingEnd + chartData.barChartValues.size * (chartData.barWidth + chartData.barDistance)).toInt(),
                heightMeasureSpec)

        initChartData()
        chartRenderer = BarChartRenderer(this, context)
    }

    fun setChartAnimationValue(value: Float, isAnimated: Boolean) {
        if (isAnimated) {
            chartRenderer.animateValue = value
        } else {
            chartRenderer.animateValue = 1f
        }
        ViewCompat.postInvalidateOnAnimation(this)
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        chartRenderer.draw(canvas)
    }

    private fun getAttrs(attrs: AttributeSet, defStyleAttr: Int) {
        val typeArray: TypedArray = context.obtainStyledAttributes(attrs, R.styleable.BarChartView, defStyleAttr, 0)
        setTypeArray(typeArray)
    }

    private fun getAttrs(attrs: AttributeSet) {
        val typedArray: TypedArray = context.obtainStyledAttributes(attrs, R.styleable.BarChartView)
        setTypeArray(typedArray)
    }

    private fun setTypeArray(typedArray: TypedArray) {

        chartData.barWidth = typedArray.getDimension(R.styleable.BarChartView_barWidth, 100f)
        chartData.barColor = typedArray.getColor(R.styleable.BarChartView_barColor, Color.WHITE)
        chartData.barDistance = typedArray.getDimension(R.styleable.BarChartView_barDistance, 20f)
        chartData.barRadius = typedArray.getDimension(R.styleable.BarChartView_barRadius, 0f)

        chartData.underLineColor = typedArray.getColor(R.styleable.BarChartView_underLineColor, Color.WHITE)
        chartData.underLineWidth = typedArray.getDimension(R.styleable.BarChartView_underLineWidth, 5f)

        chartData.valueTextColor = typedArray.getColor(R.styleable.BarChartView_valueTextColor, Color.WHITE)
        chartData.valueTextSize = typedArray.getDimension(R.styleable.BarChartView_valueTextSize, 30f)

        chartData.bottomTextColor = typedArray.getColor(R.styleable.BarChartView_bottomTextColor, Color.WHITE)
        chartData.bottomTextSize = typedArray.getDimension(R.styleable.BarChartView_bottomTextSize, 30f)

        chartData.barMaxHeight = typedArray.getDimension(R.styleable.BarChartView_barMaxHeight, 0f)

        typedArray.recycle()
    }

    private fun initChartData(){
        chartData.paddingTop = paddingTop
        chartData.paddingBottom = paddingBottom
        chartData.paddingStart = paddingStart
        chartData.paddingEnd = paddingEnd

        if(chartData.barMaxHeight == 0f){
        }
    }
    override fun getBarChartDataProvider(): BarChartData = chartData

    override fun setBarChartDataProvider(barChartData: BarChartData) {
        this.chartData = barChartData
    }

    fun startAnimation() {
        animator.startAnimation()
    }

    fun cancelAnimation() {
        animator.cancelAnimation()
    }
}