package vn.begreat.weatherforcecast.di

import com.facebook.stetho.okhttp3.StethoInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.Interceptor
import vn.begreat.data.di.NetworkInterceptor


@InstallIn(ApplicationComponent::class)
@Module
class FlavorModule {

    @NetworkInterceptor
    @Provides
    fun provideNetworkInterceptor(): Interceptor {
        return StethoInterceptor()
    }
}