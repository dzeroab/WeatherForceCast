package vn.begreat.data.network

import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import vn.begreat.data.network.dto.WeatherDto


interface WeatherApi {

    @GET("location/{woeid}/{date}")
    fun getWeathers(
        @Path("woeid") woeid: String,
        @Path("date") date: String
    ): Observable<List<WeatherDto>>
}