package vn.begreat.data.local

import io.reactivex.rxjava3.core.Observable

//
interface WeatherLocalSource {

    fun getWeathers(date: String): Observable<List<WeatherEntity>>

    fun saveWeathers(entities: List<WeatherEntity>): Observable<Boolean>

    fun getWeatherById(id: Long): Observable<WeatherEntity>
}