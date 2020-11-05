package vn.begreat.weatherforcecast.event

import androidx.lifecycle.MutableLiveData

class EventLiveData<T> : MutableLiveData<Event<T>>() {
    
    var event: T?
        get() = value?.getContentIfNotHandled()
        set(v) {
            v?.let {
                value = Event(it)
            }
        }
}