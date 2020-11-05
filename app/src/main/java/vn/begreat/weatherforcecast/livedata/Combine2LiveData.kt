package vn.begreat.weatherforcecast.livedata

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData

class Combine2LiveData<T1, T2, R>(
    live1: LiveData<T1>,
    live2: LiveData<T2>,
    combineFunc: (v1: T1?, v2: T2?) -> R
) : MediatorLiveData<R>() {

    private var value1: T1? = null;
    private var value2: T2? = null;

    init {
        addSource(live1) {
            value1 = it
            value = combineFunc(value1, value2)
        }
        addSource(live2) {
            value2 = it
            value = combineFunc(value1, value2)
        }
    }
}