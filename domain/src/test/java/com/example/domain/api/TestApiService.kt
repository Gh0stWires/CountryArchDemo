package com.example.domain.api

import com.example.domain.api.models.CountryDtoItem
import com.example.domain.api.repository.CountryService
import javax.inject.Inject

class TestApiService @Inject constructor() : CountryService {
    var failUserApi: Boolean = false
    var wrongResponse: Boolean = false

    override suspend fun getCountry(id: String?): List<CountryDtoItem> {
        if (failUserApi) throw Exception("Api failed")
        val fakeResponse: List<CountryDtoItem> = JsonProvider.objectFromJsonFileWithType(TEST_JSON)

        if (wrongResponse) return fakeResponse.apply {
            firstOrNull()?.name?.common = ""
        }

        return fakeResponse
    }

    companion object {
        private const val TEST_JSON = "/json/response.json"
    }
}