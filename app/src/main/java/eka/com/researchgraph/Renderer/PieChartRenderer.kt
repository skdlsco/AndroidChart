package eka.com.researchgraph.Renderer

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.util.Log
import eka.com.researchgraph.Animator.PieChartAnimator
import eka.com.researchgraph.PieChartDataProvider

/**
 * Created by eka on 2017. 11. 27..
 */
class PieChartRenderer(private var pieChartDataProvider: PieChartDataProvider) {
    private var outerPaint = Paint()
    private var innerPaint = Paint()
    var animateValue = 1f

    init {
        val data = pieChartDataProvider.getPieChartData()
        outerPaint.run {
            isAntiAlias = true
            style = Paint.Style.FILL
        }
        innerPaint.run {
            isAntiAlias = true
            style = Paint.Style.FILL
            color = data.innerCircleColor
        }
    }

    fun draw(canvas: Canvas, animateType: PieChartAnimator.AnimateType) {
        drawOuterCircle(canvas, animateType)
        drawInnerCircle(canvas)
    }

    private fun drawInnerCircle(canvas: Canvas) {
        val chartData = pieChartDataProvider.getPieChartData()
        val width = canvas.width
        val height = canvas.height

        canvas.drawCircle(width / 2f, height / 2f, chartData.innerRadius, innerPaint)
    }

    private fun drawOuterCircle(canvas: Canvas, animateType: PieChartAnimator.AnimateType) {
        val chartData = pieChartDataProvider.getPieChartData()
        val width = canvas.width
        val height = canvas.height
        var startAngle = 270f
        val outerCircle = RectF().apply {
            set(width / 2 - chartData.outerRadius, height / 2 - chartData.outerRadius,
                    width / 2 + chartData.outerRadius, height / 2 + chartData.outerRadius)
        }

        when (animateType) {
            PieChartAnimator.AnimateType.AnimateType1 -> {
                canvas.let {
                    for (element in chartData.pieChartValues) {
                        outerPaint.color = element.color
                        val sweepAngle = 360f / (100f / element.percentage)
                        canvas.drawArc(outerCircle, startAngle, (sweepAngle + 0.1f) * animateValue, true, outerPaint)
                        startAngle += sweepAngle
                    }
                }
            }

            PieChartAnimator.AnimateType.AnimateType2 -> {
                canvas.let {
                    for (element in chartData.pieChartValues) {
                        outerPaint.color = element.color
                        val sweepAngle = 360f / (100f / element.percentage)
                        if (animateValue >= startAngle - 270f + sweepAngle) {
                            canvas.drawArc(outerCircle, startAngle, (sweepAngle + 0.1f), true, outerPaint)
                        } else {
                            if ((sweepAngle) * (animateValue - (startAngle - 270f)) / sweepAngle > 0f)
                                canvas.drawArc(outerCircle, startAngle, (sweepAngle + 0.1f) * (animateValue - (startAngle - 270f)) / sweepAngle, true, outerPaint)
                        }
                        startAngle += sweepAngle
                    }
                }
            }
        }

    }
}