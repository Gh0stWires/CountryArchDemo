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
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
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

            Home(viewModel, scrollState, coroutineScope, navController)
        }
        composable(NavRoutes.Detail.route) { navBackStackEntry ->
            val dataId = navBackStackEntry.arguments?.getString("data")
            if (dataId != null) {
                Detail(dataId = dataId, viewModel, navController)
            }

        }

    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CountryArchDemoTheme {
    }
}