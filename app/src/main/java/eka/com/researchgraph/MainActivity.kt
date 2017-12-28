package eka.com.researchgraph

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import eka.com.researchgraph.Animator.PieChartAnimator
import eka.com.researchgraph.Listener.ChartAnimationListener
import eka.com.researchgraph.Value.BarChartValue
import eka.com.researchgraph.Value.PieChartValue
import eka.com.researchgraph.View.BarChartView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val barChartElement: ArrayList<BarChartValue> = ArrayList()
        val pieChartElement: ArrayList<PieChartValue> = ArrayList()
        btn.setOnClickListener {
            barChart.startAnimation()
        }
        btn1.setOnClickListener {
            pieChart.animateType = PieChartAnimator.AnimateType.AnimateType1
            pieChart.startAnimation()
        }
        btn2.setOnClickListener {
            pieChart.animateType = PieChartAnimator.AnimateType.AnimateType2
            pieChart.startAnimation()
        }
        pieChartElement.add(PieChartValue(Color.RED, 40.0f))
        pieChartElement.add(PieChartValue(Color.BLUE, 15.0f))
        pieChartElement.add(PieChartValue(Color.BLACK, 20.0f))
        pieChartElement.add(PieChartValue(Color.YELLOW, 15.0f))
        pieChartElement.add(PieChartValue(Color.MAGENTA, 10.0f))
        pieChart.chartData.pieChartValues = pieChartElement


        barChartElement.add(BarChartValue(100, "1"))
        barChartElement.add(BarChartValue(300, "2"))
        barChartElement.add(BarChartValue(123, "3"))
        barChartElement.add(BarChartValue(450, "4"))
        barChartElement.add(BarChartValue(200, "5"))
        barChartElement.add(BarChartValue(230, "6"))
        barChartElement.add(BarChartValue(400, "7"))
        barChartElement.add(BarChartValue(70, "8"))
        barChart.chartData.barChartValues = barChartElement
    }
}
