package com.example.countryarchdemo

sealed class HomeUiState<T>(val data: T? = null, val error: String? = null) {
    class Choosing<T>: HomeUiState<T>()
    class Loading<T>: HomeUiState<T>()
    class Error<T>(message: String): HomeUiState<T>(error = message)
    class CountryDataLoaded<T>(data: T): HomeUiState<T>(data = data)
}