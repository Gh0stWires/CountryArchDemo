package com.example.countryarchdemo


import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.api.models.SatDto
import com.example.domain.api.repository.SchoolRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repo: SchoolRepo): ViewModel() {
    var scrollPosition: Int = 0
    var offset: Int = 0
    var currentSchoolList: List<School> = emptyList()

    private val _uiState = MutableStateFlow<HomeUiState>(
        HomeUiState.Loading
    )
    val uiState: StateFlow<HomeUiState> = _uiState

    init {
        getSchools()
    }

    fun reset() {
        _uiState.value = HomeUiState.Choosing(currentSchoolList)
        viewModelScope.coroutineContext.cancelChildren() // Cancel all ongoing coroutines within this ViewModel
    }
    private fun getSchools() {
        viewModelScope.launch(Dispatchers.IO) {
            repo.getSchools().catch { ex ->
                _uiState.value = HomeUiState.Error(ex.message ?: "Error Try Again")
            }.collect { schoolDto ->
                val schoolNameList: List<School> = schoolDto.map { School(id = it.dbn, schoolName = it.schoolName ?: "Name Error") }
                currentSchoolList = schoolNameList
                _uiState.value = HomeUiState.Choosing(schoolNameList)
            }
        }
    }

    fun onClick(id: String) {
        _uiState.value = HomeUiState.DataLoaded(id)
    }

    val satDetails = mutableStateOf<SatDto?>(null)

    fun fetchDetails(id: String) {
        _uiState.value = HomeUiState.Loading
        viewModelScope.launch {
            repo.getDetails(id).catch { ex ->
                _uiState.value = HomeUiState.Error(ex.message ?: "Error loading details")
            }.collect { satDto ->
                satDetails.value = satDto.firstOrNull()
            }
        }
    }
}