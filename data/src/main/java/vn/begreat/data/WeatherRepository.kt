package vn.begreat.data

import io.reactivex.rxjava3.core.Observable
import vn.begreat.data.models.Weather

interface WeatherRepository {

    fun getWeathers(isForce: Boolean = false): Observable<List<Weather>>

    fun getWeatherDetail(id: Long): Observable<Weather>
}