package com.example.domain.api.repository

import com.example.domain.api.models.SatDto
import com.example.domain.api.models.SchoolDtoItem
import retrofit2.http.GET
import retrofit2.http.Query

interface SchoolsService {
    @GET("s3k6-pzi2.json")
    suspend fun getSchools(): List<SchoolDtoItem>

    @GET("f9bf-2cp4.json")
    suspend fun getSatScores(@Query("dbn") id: String?) : List<SatDto>
}