package vn.begreat.data.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import vn.begreat.data.DefaultWeatherRepository
import vn.begreat.data.WeatherRepository
import vn.begreat.data.local.AppDatabase
import vn.begreat.data.local.DefaultWeatherLocalSource
import vn.begreat.data.local.WeatherLocalSource
import vn.begreat.data.network.WeatherApi
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
class RepositoryModule {


    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.build(context)
    }

    @Singleton
    @Provides
    fun provideLocalSource(db: AppDatabase): WeatherLocalSource {
        return DefaultWeatherLocalSource(db.weatherDao())
    }

    @Singleton
    @Provides
    fun provideRepo(api: WeatherApi, localSource: WeatherLocalSource): WeatherRepository {
        return DefaultWeatherRepository(api, localSource)
    }
}