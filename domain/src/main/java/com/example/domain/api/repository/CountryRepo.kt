package com.example.domain.api.repository

import com.example.domain.api.models.CountryDtoItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CountryRepo @Inject constructor(private val api: CountryService) {
    suspend fun getCountry(name: String): Flow<List<CountryDtoItem>> = flow {
        emit(api.getCountry(name))
    }
}