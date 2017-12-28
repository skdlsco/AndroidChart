package eka.com.researchgraph

import eka.com.researchgraph.Data.BarChartData

/**
 * Created by eka on 2017. 11. 28..
 */
interface BarChartDataProvider {
    fun getBarChartDataProvider(): BarChartData
    fun setBarChartDataProvider(barChartData: BarChartData)
}