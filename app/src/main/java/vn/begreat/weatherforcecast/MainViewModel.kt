package vn.begreat.weatherforcecast

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import io.reactivex.rxjava3.disposables.CompositeDisposable
import vn.begreat.data.WeatherRepository
import vn.begreat.data.models.Weather
import vn.begreat.weatherforcecast.event.EventLiveData
import vn.begreat.weatherforcecast.rx.SchedulerProvider
import javax.inject.Inject

///
class MainViewModel @ViewModelInject constructor(
    private val repo: WeatherRepository,
    private val schedulerProvider: SchedulerProvider,
) : ViewModel(), OnWeatherClick {

    val weathersLive: MutableLiveData<List<Weather>> by lazy { MutableLiveData() }

    // loading
    val loadingLive: MutableLiveData<Boolean> by lazy { MutableLiveData(false) }

    val isAllowClickButton: LiveData<Boolean> by lazy {
        loadingLive.map { it != true }
    }

    val isEmpty: LiveData<Boolean> by lazy {
        weathersLive.map { it?.isEmpty() ?: true }
    }

    // error
    val exceptionLive: EventLiveData<Throwable> by lazy { EventLiveData() }
    val action: EventLiveData<MainAction> by lazy { EventLiveData() }

    private val compositeDisposable: CompositeDisposable by lazy { CompositeDisposable() }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }


    override fun onWeatherClick(weather: Weather) {
        action.event = WeatherDetailAction(weather.id)
    }

    /**
     * on Get weather click
     */
    fun onGetWeatherClick(isForce: Boolean = true) {
        loadingLive.value = true

        Log.d("dzeroab", "list baobao")

        val disposable = repo.getWeathers(isForce)
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.main())
            .doFinally {
                loadingLive.value = false
            }
            .subscribe({ list ->
                Log.d("dzeroab", "list ${list.size}")
                weathersLive.value = list ?: emptyList()
                exceptionLive.value = null
            }, {
                exceptionLive.event = it

            })

        compositeDisposable.add(disposable)
    }
}

sealed class MainAction

data class WeatherDetailAction(val id: Long) : MainAction()