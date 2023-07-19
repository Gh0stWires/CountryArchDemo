package com.example.domain.api.repository

import com.example.domain.api.models.SatDto
import com.example.domain.api.models.SchoolDtoItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SchoolRepo @Inject constructor(private val api: SchoolsService) {

    suspend fun getSchools(): Flow<List<SchoolDtoItem>> = flow {
        emit(api.getSchools())
    }

    suspend fun getDetails(id: String): Flow<List<SatDto>> = flow {
        emit(api.getSatScores(id))
    }
}