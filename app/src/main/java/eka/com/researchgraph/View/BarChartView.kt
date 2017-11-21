package eka.com.researchgraph.View

import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.content.res.TypedArray
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.ViewPropertyAnimator
import eka.com.researchgraph.R

class BarChartView : View {
    var elements: ArrayList<Elements> = ArrayList()
    var barWidth: Float = 0f
    var barMaxHeight: Float = 0f
    var barColor: Int = Color.BLACK
    var barRadius: Float = 0f
    var maxOfValue: Int = 0
    var barDistance: Float = 0f
    var underLineColor: Int = Color.WHITE
    var underLineWidth: Float = 0f
    var valueTextSize: Float = 0f
    var valueTextColor: Int = Color.WHITE
    var bottomTextColor: Int = Color.WHITE
    var bottomTextSize: Float = 0f
    private var startPoint = 0f
    private var barBottomPoint = 0f
    private var animateValue = 0f

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        getAttrs(attrs!!)
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        getAttrs(attrs!!, defStyleAttr)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        setMeasuredDimension((paddingStart + paddingEnd + elements.size * (barWidth + barDistance)).toInt(), heightMeasureSpec)
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        startPoint = paddingStart + (barDistance / 2)
        setMaxOfValue()

        barBottomPoint = height - (paddingBottom + bottomTextSize + underLineWidth + bottomTextSize * 1.7f)

        if (barMaxHeight == 0f)
            barMaxHeight = barBottomPoint - paddingTop - valueTextSize * 2

        if (barRadius >= barWidth)
            barRadius = barWidth

        val underLine = RectF(paddingStart.toFloat(), barBottomPoint, width.toFloat() - paddingEnd, barBottomPoint + underLineWidth)
        val underLinePaint = Paint()
        underLinePaint.style = Paint.Style.FILL
        underLinePaint.color = underLineColor

        canvas!!.drawRect(underLine, underLinePaint)

        for (bar in elements) {
            val barHeight = barMaxHeight * (bar.value.toFloat() / maxOfValue.toFloat()) * animateValue
            val barPaint = Paint()

            barPaint.style = Paint.Style.FILL
            barPaint.color = barColor
            //draw Bar
            val barRect = RectF(startPoint, barBottomPoint - barHeight + (barRadius * animateValue / 2.1f),
                    startPoint + barWidth, barBottomPoint)

            canvas.drawRect(barRect, barPaint)
            //draw BarRadiusLeft
            val barLeftRadiusRect = RectF(startPoint, barBottomPoint - barHeight,
                    startPoint + barRadius, barBottomPoint - barHeight + (barRadius * animateValue))
            canvas.drawArc(barLeftRadiusRect, 180f, 90f, true, barPaint)

            //draw barRadiusRight
            val barRightRadiusRect = RectF(startPoint + barWidth - barRadius, barBottomPoint - barHeight,
                    startPoint + barWidth, barBottomPoint - barHeight + (barRadius * animateValue))
            canvas.drawArc(barRightRadiusRect, 270f, 90f, true, barPaint)

            //draw barTopRect
            val barTopRect = RectF(startPoint + barRadius / 2.1f, barBottomPoint - barHeight,
                    startPoint + barWidth - barRadius / 2.1f, barBottomPoint - barHeight + (barRadius * animateValue / 2))
            canvas.drawRect(barTopRect, barPaint)
            //draw ValueText (on top text)
            val valueTextPaint = Paint()
            valueTextPaint.style = Paint.Style.FILL
            valueTextPaint.color = valueTextColor
            valueTextPaint.textSize = valueTextSize
            valueTextPaint.typeface = Typeface.createFromAsset(resources.assets, "fonts/nanum_barun_gothic_bold.ttf")


            val valueTextBounds = Rect()
            valueTextPaint.getTextBounds("" + bar.value, 0, ("" + bar.value).length, valueTextBounds)

            val valueTextStart = startPoint + (barWidth / 2) - (valueTextBounds.width() / 1.9f)
//            canvas.drawText("" + bar.value + "회", valueTextStart, barBottomPoint - valueTextBounds.height() * 1.7f, valueTextPaint)
            canvas.drawText("" + bar.value, valueTextStart, barBottomPoint - valueTextBounds.height() * 0.5f - barHeight - underLineWidth, valueTextPaint)

            val bottomTextPaint = Paint()
            bottomTextPaint.style = Paint.Style.FILL
            bottomTextPaint.color = bottomTextColor
            bottomTextPaint.textSize = bottomTextSize
            bottomTextPaint.typeface = Typeface.createFromAsset(resources.assets, "fonts/nanum_barun_gothic_bold.ttf")

            val bottomTextBounds = Rect()
            bottomTextPaint.getTextBounds(bar.bottomText, 0, bar.bottomText.length, bottomTextBounds)

            val bottomTextStart = startPoint + (barWidth / 2) - (bottomTextBounds.width() / 1.9f)
            canvas.drawText(bar.bottomText, bottomTextStart, barBottomPoint + bottomTextBounds.height() * 3f, bottomTextPaint)

            startPoint += barWidth + barDistance
        }
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

        barWidth = typedArray.getDimension(R.styleable.BarChartView_barWidth, 100f)
        barColor = typedArray.getColor(R.styleable.BarChartView_barColor, Color.WHITE)
        barDistance = typedArray.getDimension(R.styleable.BarChartView_barDistance, 20f)
        barRadius = typedArray.getDimension(R.styleable.BarChartView_barRadius, 0f)

        underLineColor = typedArray.getColor(R.styleable.BarChartView_underLineColor, Color.WHITE)
        underLineWidth = typedArray.getDimension(R.styleable.BarChartView_underLineWidth, 5f)

        valueTextColor = typedArray.getColor(R.styleable.BarChartView_valueTextColor, Color.WHITE)
        valueTextSize = typedArray.getDimension(R.styleable.BarChartView_valueTextSize, 30f)

        bottomTextColor = typedArray.getColor(R.styleable.BarChartView_bottomTextColor, Color.WHITE)
        bottomTextSize = typedArray.getDimension(R.styleable.BarChartView_bottomTextSize, 30f)

        barMaxHeight = typedArray.getDimension(R.styleable.BarChartView_barMaxHeight, 0f)

        typedArray.recycle()
    }

    private fun setMaxOfValue() {
        if (elements.isNotEmpty()) {
            maxOfValue = elements.first().value
            elements
                    .asSequence()
                    .filter { maxOfValue < it.value }
                    .forEach { maxOfValue = it.value }
        }
    }

    override fun animate(): ViewPropertyAnimator {
        val animator = ValueAnimator.ofFloat(0f, 1f)
        animator.duration = 1000
        animator.addUpdateListener {
            animateValue = it.animatedValue as Float
            invalidate()
        }
        animator.start()
        return super.animate()
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        animate()
    }

    class Elements(var value: Int, var bottomText: String)
}