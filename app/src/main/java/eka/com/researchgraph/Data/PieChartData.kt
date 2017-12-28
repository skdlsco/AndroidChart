package eka.com.researchgraph.Data

import android.graphics.Color
import eka.com.researchgraph.Value.PieChartValue

/**
 * Created by eka on 2017. 11. 27..
 */
class PieChartData{
    var pieChartValues: ArrayList<PieChartValue> = ArrayList()
    var innerCircleColor = Color.WHITE
    var outerStrokeColor = Color.WHITE
    var innerStrokeColor = Color.WHITE
    var outerStrokeWidth = 0f
    var innerStrokeWidth = 0f
    var outerRadius = 0f
    var innerRadius = 0f
}