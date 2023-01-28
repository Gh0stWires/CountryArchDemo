package com.example.domain.api.repository


import com.example.domain.api.models.CountryDtoItem
import retrofit2.http.GET
import retrofit2.http.Path

interface CountryService {
    @GET("/v3.1/name/{countryName}")
    suspend fun getCountry(@Path("countryName") id: String?): List<CountryDtoItem>
}