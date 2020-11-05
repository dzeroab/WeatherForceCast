package vn.begreat.data.network.dto

import com.google.gson.annotations.SerializedName
import vn.begreat.data.utils.DateUtils
import vn.begreat.data.utils.NumberUtils
import java.sql.Date


data class WeatherDto(
    @SerializedName("id")
    val id: Long,
    @SerializedName("weather_state_name")
    val weatherStateName: String,
    @SerializedName("created")
    val created: String,
    @SerializedName("applicable_date")
    val applicableDate: String,
    @SerializedName("wind_direction_compass")
    val windDirectionCompass: String,
    @SerializedName("min_temp")
    val minTemp: Double?,
    @SerializedName("max_temp")
    val maxTemp: Double?,
    @SerializedName("wind_speed")
    val windSpeed: Double?,
    @SerializedName("humidity")
    val humidity: Double?,
    @SerializedName("visibility")
    val visibility: Double?,
    @SerializedName("predictability")
    val predictability: Double?,
    @SerializedName("air_pressure")
    val airPressure: Double?,
) {
}


val WeatherDto.humidityString: String
    get() = NumberUtils.format(humidity ?: 0.0)


val WeatherDto.minTempString: String
    get() = NumberUtils.format(minTemp ?: 0.0)


val WeatherDto.maxTempString: String
    get() = NumberUtils.format(maxTemp ?: 0.0)

val WeatherDto.displayDate: String
    get() = DateUtils.dateToDateTime(created) ?: ""
