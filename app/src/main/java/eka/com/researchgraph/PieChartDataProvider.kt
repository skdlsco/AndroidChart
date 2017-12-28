package eka.com.researchgraph

import eka.com.researchgraph.Data.PieChartData

/**
 * Created by eka on 2017. 11. 28..
 */
interface PieChartDataProvider {
    fun setPieChartData(chartData: PieChartData)
    fun getPieChartData(): PieChartData
}