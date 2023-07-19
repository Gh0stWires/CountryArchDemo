package com.example.countryarchdemo.navigation

sealed class NavRoutes(var title: String, val route: String) {
    object Home : NavRoutes("School List",  "home")
    object Detail: NavRoutes("School Detail", "detail/{data}") {
        fun createRoute(data: String) = "detail/$data"
    }
}
