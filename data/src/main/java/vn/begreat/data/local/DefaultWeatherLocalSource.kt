package vn.begreat.data.local

import io.reactivex.rxjava3.core.Observable

///
internal class DefaultWeatherLocalSource(
    private val weatherDao: WeatherDao
) : WeatherLocalSource {
    override fun getWeathers(date: String): Observable<List<WeatherEntity>> {
        return Observable.fromCallable {
            weatherDao.queryWeathers(date)
        }
    }

    override fun saveWeathers(entities: List<WeatherEntity>): Observable<Boolean> {
        return Observable.fromCallable { weatherDao.deleteWeathers() }
            .switchMap {
                Observable.fromIterable(entities)
            }
            .switchMap { Observable.fromCallable { weatherDao.insert(it) } }
            .toList()
            .toObservable()
            .map { true }
    }


    override fun getWeatherById(id: Long): Observable<WeatherEntity> {
        return Observable.fromCallable {
            weatherDao.getById(id)
        }
    }
}