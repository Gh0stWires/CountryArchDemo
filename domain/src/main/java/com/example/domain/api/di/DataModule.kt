package com.example.domain.api.di

import android.app.Application
import com.example.domain.api.ConnectivityInterceptorImpl
import com.example.domain.api.repository.SchoolsService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    fun provideRetrofit(connectivityInterceptorImpl: ConnectivityInterceptorImpl): SchoolsService =
        Retrofit.Builder()
            .client(okHttpClient(connectivityInterceptorImpl))
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(SchoolsService::class.java)

    @Provides
    fun providesConnectivityInterceptorImpl(application: Application):
            ConnectivityInterceptorImpl {
        return ConnectivityInterceptorImpl(application)
    }

    private fun okHttpClient(connectivityInterceptorImpl: ConnectivityInterceptorImpl) =
        OkHttpClient.Builder()
            .connectTimeout(NETWORK_REQUEST_TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .readTimeout(NETWORK_REQUEST_TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .writeTimeout(NETWORK_REQUEST_TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .addInterceptor(connectivityInterceptorImpl)
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            )
            .build()

    companion object {
        const val NETWORK_REQUEST_TIMEOUT_SECONDS = 15L
        const val BASE_URL = "https://data.cityofnewyork.us/resource/"
    }
}