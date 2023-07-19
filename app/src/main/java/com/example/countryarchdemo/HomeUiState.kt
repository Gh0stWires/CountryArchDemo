package com.example.countryarchdemo

sealed class HomeUiState {
    data class Choosing(val data: List<School> = emptyList()): HomeUiState()
    object Loading: HomeUiState()
    data class Error(val error: String): HomeUiState()
    data class DataLoaded(val data: String): HomeUiState()
}