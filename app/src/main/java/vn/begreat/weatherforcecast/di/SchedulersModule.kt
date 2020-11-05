package vn.begreat.weatherforcecast.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers
import vn.begreat.weatherforcecast.rx.SchedulerProvider


@InstallIn(ApplicationComponent::class)
@Module
class SchedulersModule {

    @Provides
    fun provideScheduler(): SchedulerProvider = DefaultSchedulerProvider()
}


//
private class DefaultSchedulerProvider : SchedulerProvider {
    override fun io(): Scheduler {
        return Schedulers.io()
    }

    override fun main(): Scheduler = AndroidSchedulers.mainThread()
}