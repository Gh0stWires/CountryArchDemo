package com.example.domain.api

import com.example.domain.api.di.AppModule
import com.example.domain.api.repository.CountryService
import dagger.Binds
import dagger.Module
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(components = [SingletonComponent::class], replaces = [AppModule::class])
abstract class TestModule {
    @Binds
    @Singleton
    abstract fun providesApiServices(fakeApiService: TestApiService): CountryService
}