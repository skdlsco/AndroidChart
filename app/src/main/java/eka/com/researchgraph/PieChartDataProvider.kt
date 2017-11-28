package eka.com.researchgraph

/**
 * Created by eka on 2017. 11. 28..
 */
interface PieChartDataProvider {
    fun setPieChartData(chartData: PieChartData)
    fun getPieChartData(): PieChartData
}