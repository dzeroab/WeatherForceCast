package vn.begreat.weatherforcecast.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import vn.begreat.data.di.WeatherApiBaseUrl


@InstallIn(ApplicationComponent::class)
@Module
class AppModule {

    @WeatherApiBaseUrl
    @Provides
    fun provideApiUrl(): String {
        return "https://www.metaweather.com/api/"
    }
}