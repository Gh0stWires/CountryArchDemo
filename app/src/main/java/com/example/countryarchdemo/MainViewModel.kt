package com.example.countryarchdemo


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.api.repository.CountryRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repo: CountryRepo): ViewModel() {
    var scrollPosition: Int = 0
    var offset: Int = 0

    private val _uiState = MutableStateFlow<HomeUiState<CountryData>>(
        HomeUiState.Choosing()
    )
    val uiState: StateFlow<HomeUiState<CountryData>> = _uiState

    fun reset() {
        _uiState.value = HomeUiState.Choosing()
    }
    fun getDetails(name: String) {
        _uiState.value = HomeUiState.Loading()
        viewModelScope.launch(Dispatchers.IO) {
            repo.getCountry(name).catch { ex ->
                _uiState.value = HomeUiState.Error(ex.message ?: "Error No Network")
            }.collect {
                _uiState.value = HomeUiState.CountryDataLoaded(
                    CountryData(
                        countryName = it.firstOrNull()?.name?.common,
                        capital = it.firstOrNull()?.capital?.firstOrNull(),
                        population = it.firstOrNull()?.population.toString(),
                        area = it.firstOrNull()?.area.toString(),
                        region = it.firstOrNull()?.region,
                        subRegion = it.firstOrNull()?.subregion,

                        )
                )
            }
        }
    }
}