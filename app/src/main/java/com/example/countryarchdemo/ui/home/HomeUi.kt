package com.example.countryarchdemo.ui.home

import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.countryarchdemo.CountryData
import com.example.countryarchdemo.HomeUiState
import com.example.countryarchdemo.MainViewModel
import com.example.countryarchdemo.R
import com.example.countryarchdemo.navigation.NavRoutes
import com.example.countryarchdemo.ui.ErrorDialog
import com.example.countryarchdemo.ui.LabeledText
import com.example.countryarchdemo.ui.TopBar
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun Home(
    viewModel: MainViewModel,
    scrollState: LazyListState,
    coroutineScope: CoroutineScope,
    navController: NavHostController,
    country: Array<String>,
) {
    Scaffold(
        topBar = { TopBar() },
    ) {
        when (val state = viewModel.uiState.collectAsState(HomeUiState.Choosing<Unit>()).value) {
            is HomeUiState.Choosing<*> -> {
                DisplayCountryList(paddingValues = it, viewModel, scrollState, coroutineScope, country)
            }
            is HomeUiState.CountryDataLoaded<*> -> {
                DisposableEffect(viewModel.uiState) {
                    val countryData = state.data
                    val data = Uri.encode(Gson().toJson(countryData))
                    navController.navigate(NavRoutes.Detail.createRoute(data)) {
                        popUpTo(NavRoutes.Home.route)
                    }
                    onDispose {
                        viewModel.scrollPosition = scrollState.firstVisibleItemIndex
                        viewModel.offset = scrollState.firstVisibleItemScrollOffset
                        viewModel.reset()
                    }
                }

            }
            is HomeUiState.Error<*> -> {
                ErrorDialog(message = state.error.toString()) {
                    viewModel.reset()
                }
            }
            is HomeUiState.Loading<*> -> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator()
                }
            }
        }

    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DisplayCountryList(
    paddingValues: PaddingValues,
    viewModel: MainViewModel,
    scrollState: LazyListState,
    coroutineScope: CoroutineScope,
    country: Array<String>
) {

    LazyColumn(modifier = Modifier
        .fillMaxHeight()
        .padding(paddingValues), state = scrollState) {

        coroutineScope.launch {
            if (!scrollState.isScrollInProgress) {
                viewModel.scrollPosition = scrollState.firstVisibleItemIndex
                viewModel.offset = scrollState.firstVisibleItemScrollOffset
                scrollState.scrollToItem(viewModel.scrollPosition, viewModel.offset)
            }

        }

        items(items = country, itemContent = { name ->
            Row(modifier = Modifier.fillParentMaxWidth()) {
                Card(
                    shape = RoundedCornerShape(4.dp),
                    backgroundColor = Color.Green,
                    modifier = Modifier
                        .fillParentMaxWidth()
                        .padding(16.dp),
                    onClick = { viewModel.getDetails(name) }
                ) {
                    Text(
                        name, style = TextStyle(
                            color = Color.Black,
                            fontSize = 20.sp,
                            textAlign = TextAlign.Center
                        ), modifier = Modifier.padding(16.dp)
                    )
                }
            }
        })
    }
}

@Composable
fun Detail(data: CountryData) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.padding(16.dp))
        Text(
            data.countryName ?: stringResource(id = R.string.unknown),
            style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 44.sp),
            modifier = Modifier
                .padding(40.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.padding(10.dp))
            LabeledText(
                stringResource(id = R.string.capital_label),
                data.capital ?: stringResource(id = R.string.unknown),
            )
            Spacer(modifier = Modifier.padding(8.dp))
            LabeledText(
                stringResource(id = R.string.population_label),
                data.population ?: stringResource(id = R.string.unknown),
            )
            LabeledText(
                stringResource(id = R.string.area_label),
                data.area ?: stringResource(id = R.string.unknown),
            )
            LabeledText(
                stringResource(id = R.string.region_label),
                data.region ?: stringResource(id = R.string.unknown),
            )
            LabeledText(
                stringResource(id = R.string.sub_region_label),
                data.subRegion ?: stringResource(id = R.string.unknown),
            )

        }
    }

}