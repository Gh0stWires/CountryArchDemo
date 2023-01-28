package com.example.domain.api.models


import com.squareup.moshi.Json

    data class CountryDtoItem(
        @Json(name = "altSpellings")
        var altSpellings: List<String?>?,
        @Json(name = "area")
        var area: Double?,
        @Json(name = "borders")
        var borders: List<String?>?,
        @Json(name = "capital")
        var capital: List<String?>?,
        @Json(name = "capitalInfo")
        var capitalInfo: CapitalInfo?,
        @Json(name = "car")
        var car: Car?,
        @Json(name = "cca2")
        var cca2: String?,
        @Json(name = "cca3")
        var cca3: String?,
        @Json(name = "ccn3")
        var ccn3: String?,
        @Json(name = "cioc")
        var cioc: String?,
        @Json(name = "coatOfArms")
        var coatOfArms: CoatOfArms?,
        @Json(name = "continents")
        var continents: List<String?>?,
        @Json(name = "currencies")
        var currencies: Currencies?,
        @Json(name = "demonyms")
        var demonyms: Demonyms?,
        @Json(name = "fifa")
        var fifa: String?,
        @Json(name = "flag")
        var flag: String?,
        @Json(name = "flags")
        var flags: Flags?,
        @Json(name = "gini")
        var gini: Gini?,
        @Json(name = "idd")
        var idd: Idd?,
        @Json(name = "independent")
        var independent: Boolean?,
        @Json(name = "landlocked")
        var landlocked: Boolean?,
        @Json(name = "languages")
        var languages: Languages?,
        @Json(name = "latlng")
        var latlng: List<Double?>?,
        @Json(name = "maps")
        var maps: Maps?,
        @Json(name = "name")
        var name: Name?,
        @Json(name = "population")
        var population: Int?,
        @Json(name = "postalCode")
        var postalCode: PostalCode?,
        @Json(name = "region")
        var region: String?,
        @Json(name = "startOfWeek")
        var startOfWeek: String?,
        @Json(name = "status")
        var status: String?,
        @Json(name = "subregion")
        var subregion: String?,
        @Json(name = "timezones")
        var timezones: List<String?>?,
        @Json(name = "tld")
        var tld: List<String?>?,
        @Json(name = "translations")
        var translations: Translations?,
        @Json(name = "unMember")
        var unMember: Boolean?
    ) {
        data class CapitalInfo(
            @Json(name = "latlng")
            var latlng: List<Double?>?
        )
    
        data class Car(
            @Json(name = "side")
            var side: String?,
            @Json(name = "signs")
            var signs: List<String?>?
        )
    
        data class CoatOfArms(
            @Json(name = "png")
            var png: String?,
            @Json(name = "svg")
            var svg: String?
        )
    
        data class Currencies(
            @Json(name = "ARS")
            var aRS: ARS?
        ) {
            data class ARS(
                @Json(name = "name")
                var name: String?,
                @Json(name = "symbol")
                var symbol: String?
            )
        }
    
        data class Demonyms(
            @Json(name = "eng")
            var eng: Eng?,
            @Json(name = "fra")
            var fra: Fra?
        ) {
            data class Eng(
                @Json(name = "f")
                var f: String?,
                @Json(name = "m")
                var m: String?
            )
    
            data class Fra(
                @Json(name = "f")
                var f: String?,
                @Json(name = "m")
                var m: String?
            )
        }
    
        data class Flags(
            @Json(name = "png")
            var png: String?,
            @Json(name = "svg")
            var svg: String?
        )
    
        data class Gini(
            @Json(name = "2019")
            var x2019: Double?
        )
    
        data class Idd(
            @Json(name = "root")
            var root: String?,
            @Json(name = "suffixes")
            var suffixes: List<String?>?
        )
    
        data class Languages(
            @Json(name = "grn")
            var grn: String?,
            @Json(name = "spa")
            var spa: String?
        )
    
        data class Maps(
            @Json(name = "googleMaps")
            var googleMaps: String?,
            @Json(name = "openStreetMaps")
            var openStreetMaps: String?
        )
    
        data class Name(
            @Json(name = "common")
            var common: String?,
            @Json(name = "nativeName")
            var nativeName: NativeName?,
            @Json(name = "official")
            var official: String?
        ) {
            data class NativeName(
                @Json(name = "grn")
                var grn: Grn?,
                @Json(name = "spa")
                var spa: Spa?
            ) {
                data class Grn(
                    @Json(name = "common")
                    var common: String?,
                    @Json(name = "official")
                    var official: String?
                )
    
                data class Spa(
                    @Json(name = "common")
                    var common: String?,
                    @Json(name = "official")
                    var official: String?
                )
            }
        }
    
        data class PostalCode(
            @Json(name = "format")
            var format: String?,
            @Json(name = "regex")
            var regex: String?
        )
    
        data class Translations(
            @Json(name = "ara")
            var ara: Ara?,
            @Json(name = "bre")
            var bre: Bre?,
            @Json(name = "ces")
            var ces: Ces?,
            @Json(name = "cym")
            var cym: Cym?,
            @Json(name = "deu")
            var deu: Deu?,
            @Json(name = "est")
            var est: Est?,
            @Json(name = "fin")
            var fin: Fin?,
            @Json(name = "fra")
            var fra: Fra?,
            @Json(name = "hrv")
            var hrv: Hrv?,
            @Json(name = "hun")
            var hun: Hun?,
            @Json(name = "ita")
            var ita: Ita?,
            @Json(name = "jpn")
            var jpn: Jpn?,
            @Json(name = "kor")
            var kor: Kor?,
            @Json(name = "nld")
            var nld: Nld?,
            @Json(name = "per")
            var per: Per?,
            @Json(name = "pol")
            var pol: Pol?,
            @Json(name = "por")
            var por: Por?,
            @Json(name = "rus")
            var rus: Rus?,
            @Json(name = "slk")
            var slk: Slk?,
            @Json(name = "spa")
            var spa: Spa?,
            @Json(name = "swe")
            var swe: Swe?,
            @Json(name = "tur")
            var tur: Tur?,
            @Json(name = "urd")
            var urd: Urd?,
            @Json(name = "zho")
            var zho: Zho?
        ) {
            data class Ara(
                @Json(name = "common")
                var common: String?,
                @Json(name = "official")
                var official: String?
            )
    
            data class Bre(
                @Json(name = "common")
                var common: String?,
                @Json(name = "official")
                var official: String?
            )
    
            data class Ces(
                @Json(name = "common")
                var common: String?,
                @Json(name = "official")
                var official: String?
            )
    
            data class Cym(
                @Json(name = "common")
                var common: String?,
                @Json(name = "official")
                var official: String?
            )
    
            data class Deu(
                @Json(name = "common")
                var common: String?,
                @Json(name = "official")
                var official: String?
            )
    
            data class Est(
                @Json(name = "common")
                var common: String?,
                @Json(name = "official")
                var official: String?
            )
    
            data class Fin(
                @Json(name = "common")
                var common: String?,
                @Json(name = "official")
                var official: String?
            )
    
            data class Fra(
                @Json(name = "common")
                var common: String?,
                @Json(name = "official")
                var official: String?
            )
    
            data class Hrv(
                @Json(name = "common")
                var common: String?,
                @Json(name = "official")
                var official: String?
            )
    
            data class Hun(
                @Json(name = "common")
                var common: String?,
                @Json(name = "official")
                var official: String?
            )
    
            data class Ita(
                @Json(name = "common")
                var common: String?,
                @Json(name = "official")
                var official: String?
            )
    
            data class Jpn(
                @Json(name = "common")
                var common: String?,
                @Json(name = "official")
                var official: String?
            )
    
            data class Kor(
                @Json(name = "common")
                var common: String?,
                @Json(name = "official")
                var official: String?
            )
    
            data class Nld(
                @Json(name = "common")
                var common: String?,
                @Json(name = "official")
                var official: String?
            )
    
            data class Per(
                @Json(name = "common")
                var common: String?,
                @Json(name = "official")
                var official: String?
            )
    
            data class Pol(
                @Json(name = "common")
                var common: String?,
                @Json(name = "official")
                var official: String?
            )
    
            data class Por(
                @Json(name = "common")
                var common: String?,
                @Json(name = "official")
                var official: String?
            )
    
            data class Rus(
                @Json(name = "common")
                var common: String?,
                @Json(name = "official")
                var official: String?
            )
    
            data class Slk(
                @Json(name = "common")
                var common: String?,
                @Json(name = "official")
                var official: String?
            )
    
            data class Spa(
                @Json(name = "common")
                var common: String?,
                @Json(name = "official")
                var official: String?
            )
    
            data class Swe(
                @Json(name = "common")
                var common: String?,
                @Json(name = "official")
                var official: String?
            )
    
            data class Tur(
                @Json(name = "common")
                var common: String?,
                @Json(name = "official")
                var official: String?
            )
    
            data class Urd(
                @Json(name = "common")
                var common: String?,
                @Json(name = "official")
                var official: String?
            )
    
            data class Zho(
                @Json(name = "common")
                var common: String?,
                @Json(name = "official")
                var official: String?
            )
        }
    }