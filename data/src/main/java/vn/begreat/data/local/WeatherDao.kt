package vn.begreat.data.local

import androidx.room.*

@Dao
interface WeatherDao {

    @Query("SELECT * FROM ${RoomDbConstant.Tables.WEATHER} WHERE `date` = (:date)")
    fun queryWeathers(date: String): List<WeatherEntity>

    @Query("DELETE FROM ${RoomDbConstant.Tables.WEATHER}")
    fun deleteWeathers()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(entity: WeatherEntity)

    @Query("SELECT * FROM ${RoomDbConstant.Tables.WEATHER} WHERE weather_id=:weatherId")
    fun getById(weatherId: Long): WeatherEntity
}