package com.example.countryarchdemo.navigation

sealed class NavRoutes(var title: String, val route: String) {
    object Home : NavRoutes("Country List",  "home")
    object Detail: NavRoutes("Country Detail", "detail/{data}") {
        fun createRoute(data: String) = "detail/$data"
    }
}
