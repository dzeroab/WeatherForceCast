package vn.begreat.weatherforcecast.rx

import io.reactivex.rxjava3.core.Scheduler

interface SchedulerProvider {

    fun io(): Scheduler

    fun main(): Scheduler
}