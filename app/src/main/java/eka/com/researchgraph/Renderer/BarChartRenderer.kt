package eka.com.researchgraph.Renderer

import android.content.Context
import android.graphics.*
import eka.com.researchgraph.BarChartDataProvider

/**
 * Created by eka on 2017. 11. 28..
 */
class BarChartRenderer(var barChartDataProvider: BarChartDataProvider, private val context: Context) {
    private var underLinePaint = Paint()
    private var barPaint = Paint()
    private var valueTextPaint = Paint()
    private var bottomTextPaint = Paint()
    private var maxOfValue = 0
    var animateValue = 1f

    init {
        val data = barChartDataProvider.getBarChartDataProvider()
        underLinePaint.run {
            isAntiAlias = true
            style = Paint.Style.FILL
            color = data.underLineColor
        }
        barPaint.run {
            isAntiAlias = true
            style = Paint.Style.FILL
            color = data.barColor
        }
        valueTextPaint.run {
            isAntiAlias = true
            style = Paint.Style.FILL
            color = data.valueTextColor
            textSize = data.valueTextSize
            typeface = Typeface.createFromAsset(context.resources.assets, "fonts/nanum_barun_gothic_bold.ttf")
        }
        bottomTextPaint.run {
            isAntiAlias = true
            style = Paint.Style.FILL
            color = data.bottomTextColor
            textSize = data.bottomTextSize
            typeface = Typeface.createFromAsset(context.resources.assets, "fonts/nanum_barun_gothic_bold.ttf")
        }
    }

    fun draw(canvas: Canvas) {
        setDrawValue()
        drawBottomLine(canvas)
        drawBottomText(canvas)
        drawBar(canvas)
        drawValueText(canvas)
    }

    private fun drawBar(canvas: Canvas) {
        val data = barChartDataProvider.getBarChartDataProvider()
        var startPoint = data.paddingStart + (data.barDistance / 2)
        val barBottomPoint = canvas.height - (data.paddingBottom + data.bottomTextSize + data.underLineWidth + data.bottomTextSize * 1.7f)
        val barMaxHeight = barBottomPoint - data.paddingTop - data.valueTextSize * 2

        for (barValue in data.barChartValues) {
            val barHeight = barMaxHeight * (barValue.value.toFloat() / maxOfValue.toFloat()) * animateValue
            val barPaint = Paint().apply {
                style = Paint.Style.FILL
                color = data.barColor
            }
            //draw Bar
            val barRect = RectF(startPoint, barBottomPoint - barHeight + (data.barRadius * animateValue / 2.1f),
                    startPoint + data.barWidth, barBottomPoint)

            canvas.drawRect(barRect, barPaint)
            //draw BarRadiusLeft
            val barLeftRadiusRect = RectF(startPoint, barBottomPoint - barHeight,
                    startPoint + data.barRadius, barBottomPoint - barHeight + (data.barRadius * animateValue))
            canvas.drawArc(barLeftRadiusRect, 180f, 90f, true, barPaint)

            //draw barRadiusRight
            val barRightRadiusRect = RectF(startPoint + data.barWidth - data.barRadius, barBottomPoint - barHeight,
                    startPoint + data.barWidth, barBottomPoint - barHeight + (data.barRadius * animateValue))
            canvas.drawArc(barRightRadiusRect, 270f, 90f, true, barPaint)

            //draw barTopRect
            val barTopRect = RectF(startPoint + data.barRadius / 2.1f, barBottomPoint - barHeight,
                    startPoint + data.barWidth - data.barRadius / 2.1f, barBottomPoint - barHeight + (data.barRadius * animateValue / 2))
            canvas.drawRect(barTopRect, barPaint)

            startPoint += data.barWidth + data.barDistance
        }
    }

    private fun drawBottomLine(canvas: Canvas) {
        val data = barChartDataProvider.getBarChartDataProvider()
        val barBottomPoint = canvas.height - (data.paddingBottom + data.bottomTextSize + data.underLineWidth + data.bottomTextSize * 1.7f)
        val underLine = RectF(data.paddingStart.toFloat(), barBottomPoint,
                canvas.width.toFloat() - data.paddingEnd, barBottomPoint + data.underLineWidth)
        canvas.drawRect(underLine, underLinePaint)
    }

    private fun drawBottomText(canvas: Canvas) {
        val data = barChartDataProvider.getBarChartDataProvider()
        var startPoint = data.paddingStart + (data.barDistance / 2)
        val barBottomPoint = canvas.height - (data.paddingBottom + data.bottomTextSize + data.underLineWidth + data.bottomTextSize * 1.7f)

        for (barValue in data.barChartValues) {
            val bottomTextBounds = Rect()
            bottomTextPaint.getTextBounds(barValue.bottomText, 0, barValue.bottomText.length, bottomTextBounds)

            val bottomTextStart = startPoint + (data.barWidth / 2) - (bottomTextBounds.width() / 1.9f)
            canvas.drawText(barValue.bottomText, bottomTextStart, barBottomPoint + bottomTextBounds.height() * 3f, bottomTextPaint)
            startPoint += data.barWidth + data.barDistance

        }
    }

    private fun drawValueText(canvas: Canvas) {
        val data = barChartDataProvider.getBarChartDataProvider()
        var startPoint = data.paddingStart + (data.barDistance / 2)
        val barBottomPoint = canvas.height - (data.paddingBottom + data.bottomTextSize + data.underLineWidth + data.bottomTextSize * 1.7f)
        val barMaxHeight = barBottomPoint - data.paddingTop - data.valueTextSize * 2

        for (barValue in data.barChartValues) {
            val barHeight = barMaxHeight * (barValue.value.toFloat() / maxOfValue.toFloat()) * animateValue
            val valueTextBounds = Rect()
            valueTextPaint.getTextBounds("" + barValue.value, 0, ("" + barValue.value).length, valueTextBounds)

            val valueTextStart = startPoint + (data.barWidth / 2) - (valueTextBounds.width() / 1.9f)
            canvas.drawText("" + barValue.value, valueTextStart,
                    barBottomPoint - valueTextBounds.height() * 0.5f - barHeight - data.underLineWidth, valueTextPaint)
            startPoint += data.barWidth + data.barDistance
        }
    }

    private fun setDrawValue() {
        val data = barChartDataProvider.getBarChartDataProvider()
        if (data.barChartValues.isNotEmpty()) {
            maxOfValue = data.barChartValues.first().value
            data.barChartValues
                    .asSequence()
                    .filter { maxOfValue < it.value }
                    .forEach { maxOfValue = it.value }
        }

    }
}
