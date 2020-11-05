package vn.begreat.data.models

import vn.begreat.data.local.WeatherEntity
import vn.begreat.data.network.dto.*
import vn.begreat.data.utils.NumberUtils

data class Weather(
    val id: Long,
    val date: String,
    val humidity: String,
    val minTemp: String,
    val maxTemp: String,
    val weatherStateName: String,
    val displayDate: String,
    //
    val applicableDate: String,
    val windDirectionCompass: String,
    val windSpeed: Double,
    val visibility: Double,
    val predictability: Double,
    val airPressure: Double,
) {

    val predictabilityText: String
        get() = NumberUtils.format(predictability)

    val visibilityText: String
        get() = NumberUtils.format(visibility)


    val airPressureText: String
        get() = NumberUtils.format(airPressure)

    val windSpeedText: String
        get() = NumberUtils.format(windSpeed)


    companion object {
        fun fromDto(date: String, dto: WeatherDto): Weather {
            return Weather(
                dto.id,
                date,
                dto.humidityString,
                dto.minTempString,
                dto.maxTempString,
                dto.weatherStateName,
                dto.displayDate,
                dto.applicableDate,
                dto.windDirectionCompass,
                dto.windSpeed ?: 0.0,
                dto.visibility ?: 0.0,
                dto.predictability ?: 0.0,
                dto.airPressure ?: 0.0,
            )
        }

        fun fromEntity(entity: WeatherEntity): Weather {
            return Weather(
                entity.weatherId,
                entity.date,
                entity.humidity,
                entity.minTemp,
                entity.maxTemp,
                entity.weatherStateName,
                entity.displayDate,

                entity.applicableDate,
                entity.windDirectionCompass,
                entity.windSpeed,
                entity.visibility,
                entity.predictability,
                entity.airPressure,
            )
        }
    }
}


fun Weather.toEntity(): WeatherEntity {
    return WeatherEntity(
        0,
        id,
        date,
        humidity,
        minTemp,
        maxTemp,
        weatherStateName,
        displayDate,

        applicableDate,
        windDirectionCompass, windSpeed, visibility, predictability, airPressure
    )
}