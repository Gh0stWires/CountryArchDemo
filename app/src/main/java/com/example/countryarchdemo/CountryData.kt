package com.example.countryarchdemo

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CountryData(
    var countryName: String?,
    var capital: String?,
    var population: String?,
    var area: String?,
    var region: String?,
    var subRegion: String?,
) : Parcelable
