package vn.begreat.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

///
@Entity(tableName = RoomDbConstant.Tables.WEATHER)
data class WeatherEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "weather_id") val weatherId: Long,
    @ColumnInfo(name = "date") val date: String,
    @ColumnInfo(name = "humidity") val humidity: String,
    @ColumnInfo(name = "minTemp") val minTemp: String,
    @ColumnInfo(name = "maxTemp") val maxTemp: String,
    @ColumnInfo(name = "weatherStateName") val weatherStateName: String,
    @ColumnInfo(name = "displayDate") val displayDate: String,

    @ColumnInfo(name = "applicableDate") val applicableDate: String,
    @ColumnInfo(name = "windDirectionCompass") val windDirectionCompass: String,
    @ColumnInfo(name = "windSpeed") val windSpeed: Double,
    @ColumnInfo(name = "visibility") val visibility: Double,
    @ColumnInfo(name = "predictability") val predictability: Double,
    @ColumnInfo(name = "airPressure") val airPressure: Double,
) {
}