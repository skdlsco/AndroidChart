package eka.com.researchgraph.View

import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.ViewPropertyAnimator

/**
 * Created by eka on 2017. 11. 20..
 */
class MyCustomView : View {
    var squareWidth = 0
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        squareWidth = paddingRight
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        val rectPaint = Paint(Color.BLUE)
        val rect = Rect(paddingLeft, paddingTop, squareWidth , height - paddingBottom)
        canvas?.drawRect(rect, rectPaint)
    }

    override fun animate(): ViewPropertyAnimator {
        val animator = ValueAnimator.ofInt(paddingLeft, width - paddingRight)
        animator.duration = 2000
        animator.addUpdateListener {
            squareWidth = it.animatedValue as Int
            invalidate()
        }
        animator.start()
        return super.animate()
    }
}