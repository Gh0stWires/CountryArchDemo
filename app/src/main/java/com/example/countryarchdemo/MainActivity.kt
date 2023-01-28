package com.example.countryarchdemo

import androidx.hilt.navigation.compose.hiltViewModel
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.countryarchdemo.navigation.CountryDataType
import com.example.countryarchdemo.navigation.NavRoutes
import com.example.countryarchdemo.ui.home.Detail
import com.example.countryarchdemo.ui.home.Home
import com.example.countryarchdemo.ui.theme.CountryArchDemoTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CountryArchDemoTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}



@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val scrollState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    val viewModel = hiltViewModel<MainViewModel>()

    NavHost(navController = navController, startDestination = NavRoutes.Home.route) {
        composable(NavRoutes.Home.route) {
            Home(viewModel, scrollState, coroutineScope, navController, stringArrayResource(id = R.array.countries_array))
        }
        composable(
            NavRoutes.Detail.route,
            arguments = listOf(navArgument("data") { type = CountryDataType() })
        ) {
            val data = it.arguments?.getParcelable<CountryData>("data")
            if (data != null) {
                Detail(data = data)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CountryArchDemoTheme {
        Detail(data = CountryData("unknown", "unknown", "unknown", "unknown", "unknown", "unknown",))
    }
}