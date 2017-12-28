package eka.com.researchgraph.Data

import android.graphics.Color
import eka.com.researchgraph.Value.BarChartValue

/**
 * Created by eka on 2017. 11. 28..
 */
class BarChartData {
    var barChartValues: ArrayList<BarChartValue> = ArrayList()
    var paddingTop: Int = 0
    var paddingBottom: Int = 0
    var paddingEnd: Int = 0
    var paddingStart: Int = 0
    var barWidth: Float = 0f
    var barMaxHeight: Float = 0f
    var barColor: Int = Color.BLACK
    var barRadius: Float = 0f
    var barDistance: Float = 0f
    var underLineColor: Int = Color.WHITE
    var underLineWidth: Float = 0f
    var valueTextSize: Float = 0f
    var valueTextColor: Int = Color.WHITE
    var bottomTextColor: Int = Color.WHITE
    var bottomTextSize: Float = 0f
}