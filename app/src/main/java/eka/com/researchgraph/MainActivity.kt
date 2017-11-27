package eka.com.researchgraph

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import eka.com.researchgraph.View.BarChartView
import eka.com.researchgraph.View.PieChartView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val barChartElement: ArrayList<BarChartView.Elements> = ArrayList()
        val pieChartElement: ArrayList<PieChartView.Element> = ArrayList()
        btn.setOnClickListener {
            barChart.animate()
        }

        btn1.setOnClickListener {
            pieChart.animateType = PieChartView.AnimateType.AnimateType1
            pieChart.animate()
        }
        btn2.setOnClickListener {
            pieChart.animateType = PieChartView.AnimateType.AnimateType2
            pieChart.animate()
        }
        pieChartElement.add(PieChartView.Element(Color.RED, 40.0f))
        pieChartElement.add(PieChartView.Element(Color.BLUE, 15.0f))
        pieChartElement.add(PieChartView.Element(Color.BLACK, 20.0f))
        pieChartElement.add(PieChartView.Element(Color.YELLOW, 15.0f))
        pieChartElement.add(PieChartView.Element(Color.MAGENTA, 10.0f))
        pieChart.elements = pieChartElement



        barChartElement.add(BarChartView.Elements(100, "1"))
        barChartElement.add(BarChartView.Elements(300, "2"))
        barChartElement.add(BarChartView.Elements(123, "3"))
        barChartElement.add(BarChartView.Elements(450, "4"))
        barChartElement.add(BarChartView.Elements(200, "5"))
        barChartElement.add(BarChartView.Elements(230, "6"))
        barChartElement.add(BarChartView.Elements(400, "7"))
        barChartElement.add(BarChartView.Elements(70, "8"))
        barChart.elements = barChartElement
    }
}
