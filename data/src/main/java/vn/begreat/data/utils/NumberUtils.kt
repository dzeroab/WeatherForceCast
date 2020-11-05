package vn.begreat.data.utils

import java.text.DecimalFormat

object NumberUtils {

    private val decimalFormat: DecimalFormat by lazy {
        DecimalFormat("#.00")
    }

    fun format(number: Double): String {
        return decimalFormat.format(number)
    }
}