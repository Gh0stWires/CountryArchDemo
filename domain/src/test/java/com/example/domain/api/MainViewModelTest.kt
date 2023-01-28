package com.example.domain.api

import com.example.countryarchdemo.MainViewModel
import com.example.domain.api.repository.CountryRepo
import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import junit.framework.Assert.assertNull
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.test.*
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.LooperMode
import javax.inject.Inject

@HiltAndroidTest
@RunWith(RobolectricTestRunner::class)
@Config(sdk = [25], application = HiltTestApplication::class, manifest=Config.NONE)
@LooperMode(LooperMode.Mode.PAUSED)
class MainViewModelTest {
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var apiRepository: CountryRepo
    @Mock
    private lateinit var homeViewModel: MainViewModel

    @BindValue
    @JvmField
    val fakeApiService: TestApiService = TestApiService()

    @Before
    fun setup() {
        hiltRule.inject()
        homeViewModel = MainViewModel(apiRepository)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `test get country success`() = runTest {

        val response = apiRepository.getCountry("Argentina")
        advanceUntilIdle()
        assert(response.firstOrNull()?.firstOrNull()?.name?.common != null)

    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `test get country failure`() = runTest {
        fakeApiService.failUserApi = true
        homeViewModel.getDetails("Argentina")
        val response = homeViewModel.uiState.value
        advanceUntilIdle()
        assertNull(response.data)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `test wrong data`() = runTest {
        fakeApiService.wrongResponse = true
        val response = apiRepository.getCountry("Argentina")
        advanceUntilIdle()
        assertEquals("", response.firstOrNull()?.firstOrNull()?.name?.common)
    }
}