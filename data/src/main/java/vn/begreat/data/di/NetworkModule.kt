package vn.begreat.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import vn.begreat.data.network.DefaultNetworkStatusProvider
import vn.begreat.data.network.NetworkStatusProvider
import vn.begreat.data.network.WeatherApi
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
class NetworkModule {

    @Provides
    fun provideNetworkStatus(): NetworkStatusProvider {
        return DefaultNetworkStatusProvider()
    }

    @Provides
    fun providerConverterFactory(): Converter.Factory {
        return GsonConverterFactory.create()
    }

    @Provides
    fun providerAdapterFactory(): CallAdapter.Factory {
        return RxJava3CallAdapterFactory.create()
    }

    @Provides
    fun provideClient(
    ): OkHttpClient {
        return OkHttpClient.Builder().build()
    }

    @Provides
    fun provideRetrofitBuilder(
        @WeatherApiBaseUrl baseUrl: String,
        callAdapter: CallAdapter.Factory,
        converter: Converter.Factory,
        client: OkHttpClient
    ): Retrofit.Builder {

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addCallAdapterFactory(callAdapter)
            .addConverterFactory(converter)
    }

    @Singleton
    @Provides
    fun buildApi(retrofit: Retrofit.Builder): WeatherApi {
        return retrofit
            .build()
            .create(WeatherApi::class.java)
    }


//    private fun buildInterceptor(
//        networkStatusProvider: NetworkStatusProvider,
//        context: Context
//    ): Interceptor = Interceptor {
//        val request = it.request()
//
//        val newRequestBuilder = request.newBuilder().apply {
//            if (networkStatusProvider.isNetworkAvailable(context)) {
//                // 1 minutes
//                header("Cache-Control", "public, max-age=" + 60)
//            } else {
//                /// valid cache in 1 week
//                header("Cache-Control", "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7)
//            }
//        }
//
//        val newRequest = newRequestBuilder
//            .build()
//        return@Interceptor it.proceed(newRequest)
//    }
}