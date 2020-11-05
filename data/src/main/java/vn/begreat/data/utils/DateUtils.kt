package vn.begreat.data.utils

import android.util.Log
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

object DateUtils {

    //    "yyyy-MM-dd"
    private val dateFormat: DateFormat = SimpleDateFormat("yyyy/MM/dd", Locale.US)
    private val dateTimeFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd hh:mm", Locale.US)
    private var fullDateTime: DateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US)

    fun getYtdDate(): String? {
        try {
            val calendarDate = Calendar.getInstance()
            calendarDate.add(Calendar.DAY_OF_MONTH, -1)
            return dateFormat.format(calendarDate.time)
        } catch (e: Throwable) {
            Log.e("dzeroab", "date ", e)

        }
        return null
    }

    fun dateToDateTime(date: String): String? {
        try {
            val d = dateFromZTime(date) ?: return null
            val calendarDate = Calendar.getInstance()
            calendarDate.timeInMillis = d.time
            return dateTimeFormat.format(calendarDate.time)
        } catch (e: Throwable) {
        }
        return null
    }

    private fun dateFromZTime(date: String): Date? {
        try {
            return fullDateTime.parse(date)
        } catch (e: Throwable) {
            Log.e("dzeroab", "date 1 ", e)
        }
        return null
    }
}