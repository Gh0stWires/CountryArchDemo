package com.example.countryarchdemo.ui.home

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.countryarchdemo.HomeUiState
import com.example.countryarchdemo.MainViewModel
import com.example.countryarchdemo.School
import com.example.countryarchdemo.navigation.NavRoutes
import com.example.countryarchdemo.ui.ErrorDialog
import com.example.countryarchdemo.ui.TopBar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun Home(
    viewModel: MainViewModel,
    scrollState: LazyListState,
    coroutineScope: CoroutineScope,
    navController: NavHostController,
) {
    Scaffold(
        topBar = { TopBar() },
    ) {
        when (val state = viewModel.uiState.collectAsState().value) {
            is HomeUiState.Choosing -> {
                DisplaySchoolList(paddingValues = it, viewModel, scrollState, coroutineScope,
                    state.data
                )
            }
            is HomeUiState.DataLoaded -> {
                DisposableEffect(state.data) {
                    val school = state.data
                    navController.navigate(NavRoutes.Detail.createRoute(school)) {
                        popUpTo(NavRoutes.Home.route)
                    }
                    onDispose {
                        viewModel.scrollPosition = scrollState.firstVisibleItemIndex
                        viewModel.offset = scrollState.firstVisibleItemScrollOffset
                    }
                }
            }
            is HomeUiState.Error -> {
                ErrorDialog(message = state.error) {
                    viewModel.reset()
                }
            }
            is HomeUiState.Loading -> {
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
fun DisplaySchoolList(
    paddingValues: PaddingValues,
    viewModel: MainViewModel,
    scrollState: LazyListState,
    coroutineScope: CoroutineScope,
    school: List<School>
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

        items(items = school, itemContent = { name ->
            Row(modifier = Modifier.fillParentMaxWidth()) {
                Card(
                    shape = RoundedCornerShape(4.dp),
                    backgroundColor = Color.Green,
                    modifier = Modifier
                        .fillParentMaxWidth()
                        .padding(16.dp),
                    onClick = { viewModel.onClick(name.id)}
                ) {
                    Text(
                        name.schoolName, style = TextStyle(
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
fun Detail(dataId: String, viewModel: MainViewModel, navController: NavHostController) {
    val satDetails = viewModel.satDetails.value
    LaunchedEffect(dataId) {
        viewModel.fetchDetails(dataId)
    }

    // Register a callback to the back button press
    BackHandler {
        // Navigate back to the Home screen
        viewModel.reset()
        navController.popBackStack()
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(text = "Name: ${satDetails?.schoolName ?: "Error Not Found"}", style = MaterialTheme.typography.h5)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Math Score: ${satDetails?.satMathAvgScore ?: "Error Not Found"}", style = MaterialTheme.typography.h5)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Writing Score: ${satDetails?.satWritingAvgScore ?: "Error Not Found"}", style = MaterialTheme.typography.h5)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Reading Score: ${satDetails?.satCriticalReadingAvgScore ?: "Error Not Found"}", style = MaterialTheme.typography.h5)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Number of Test Takers: ${satDetails?.numOfSatTestTakers ?: "Error Not Found"}", style = MaterialTheme.typography.h5)
        }
    }
}