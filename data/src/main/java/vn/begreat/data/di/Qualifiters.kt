package vn.begreat.data.di

import javax.inject.Qualifier


@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class WeatherApiBaseUrl

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class NetworkInterceptor