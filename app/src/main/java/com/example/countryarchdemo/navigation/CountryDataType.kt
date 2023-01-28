package com.example.countryarchdemo.navigation

import android.os.Bundle
import androidx.navigation.NavType
import com.example.countryarchdemo.CountryData
import com.google.gson.Gson

class CountryDataType : NavType<CountryData>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): CountryData? {
        return bundle.getParcelable(key)
    }
    override fun parseValue(value: String): CountryData {
        return Gson().fromJson(value, CountryData::class.java)
    }
    override fun put(bundle: Bundle, key: String, value: CountryData) {
        bundle.putParcelable(key, value)
    }
}