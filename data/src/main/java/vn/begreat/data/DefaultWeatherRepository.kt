package vn.begreat.data

import android.util.Log
import io.reactivex.rxjava3.core.Observable
import retrofit2.HttpException
import vn.begreat.data.exceptions.NoConnectionException
import vn.begreat.data.local.WeatherLocalSource
import vn.begreat.data.models.Weather
import vn.begreat.data.models.toEntity
import vn.begreat.data.network.WeatherApi
import vn.begreat.data.utils.DateUtils
import java.net.UnknownHostException

///
internal class DefaultWeatherRepository(
    private val api: WeatherApi,
    private val localSource: WeatherLocalSource
) : WeatherRepository {
    //
    override fun getWeathers(isForce: Boolean): Observable<List<Weather>> {
        val date = DateUtils.getYtdDate() ?: ""
        Log.d("dzeroab", "date $date")
        val obs = if (!isForce) getLocalWeathers(date)
        else Observable.concat(
            listOf(
                getLocalWeathers(date),
                getRemoteWeathers(date)
            )
        )
        return obs.onErrorResumeNext { e ->
            Observable.error(mapErrorToInternalError(e))
        }
    }

    override fun getWeatherDetail(id: Long): Observable<Weather> {
        return localSource.getWeatherById(id)
            .map { Weather.fromEntity(it) }
    }


    /**
     *
     */
    private fun getLocalWeathers(date: String): Observable<List<Weather>> {
        return localSource.getWeathers(date)
            .map { it?.map { entity -> Weather.fromEntity(entity) } ?: emptyList() }

    }

    /**
     *
     */
    private fun getRemoteWeathers(date: String): Observable<List<Weather>> {
        return api.getWeathers(BRISBANE_WOEID, date)
            .map { it?.map { dto -> Weather.fromDto(date, dto) } ?: emptyList() }
            .switchMap { weathers ->
                /// save to local but ignore the local error
                localSource.saveWeathers(weathers.map { weather -> weather.toEntity() })
                    .onErrorResumeNext { Observable.just(true) }
                    .switchMap {
                        Observable.just(weathers)
                    }
            }
    }

    ///
    private fun mapErrorToInternalError(e: Throwable): Throwable {
        if (e is HttpException && e.code() == 504 || e is UnknownHostException) {
            return NoConnectionException()
        }
        return e
    }

    companion object {
        private const val BRISBANE_WOEID = "1100661"
    }

}