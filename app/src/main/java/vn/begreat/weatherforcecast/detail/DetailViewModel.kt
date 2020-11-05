package vn.begreat.weatherforcecast.detail

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import io.reactivex.rxjava3.disposables.CompositeDisposable
import vn.begreat.data.WeatherRepository
import vn.begreat.data.models.Weather
import vn.begreat.weatherforcecast.rx.SchedulerProvider

class DetailViewModel @ViewModelInject constructor(
    private val repo: WeatherRepository,
    private val schedulerProvider: SchedulerProvider,
) : ViewModel() {

    private val compositeDisposable: CompositeDisposable by lazy { CompositeDisposable() }

    private val weatherLive: MutableLiveData<Weather> by lazy { MutableLiveData() }

    val weatherState: LiveData<String> by lazy {
        weatherLive.map { it?.weatherStateName ?: "-" }
    }
    val windDirection: LiveData<String> by lazy {
        weatherLive.map { it?.windDirectionCompass ?: "-" }
    }
    val windSpeed: LiveData<String> by lazy {
        weatherLive.map { it?.windSpeedText ?: "-" }
    }
    val minTemp: LiveData<String> by lazy {
        weatherLive.map { it?.minTemp ?: "-" }
    }
    val maxTemp: LiveData<String> by lazy {
        weatherLive.map { it?.maxTemp ?: "-" }
    }

    val pressure: LiveData<String> by lazy {
        weatherLive.map { it?.airPressureText ?: "-" }
    }

    val humidity: LiveData<String> by lazy {
        weatherLive.map { it?.humidity ?: "-" }
    }

    val visibility: LiveData<String> by lazy {
        weatherLive.map { it?.visibilityText ?: "-" }
    }
    val predictability: LiveData<String> by lazy {
        weatherLive.map { it?.predictabilityText ?: "-" }
    }

    val applicableDate: LiveData<String> by lazy {
        weatherLive.map { it?.applicableDate ?: "-" }
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

    fun getWeatherDetail(id: Long) {
        val disposable = repo.getWeatherDetail(id)
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.main())
            .subscribe({
                weatherLive.value = it
            }, {
                Log.e("dzeroab", "", it)
            })

        compositeDisposable.add(disposable)
    }

}