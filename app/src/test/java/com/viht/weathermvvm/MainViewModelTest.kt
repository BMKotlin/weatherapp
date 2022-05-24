package com.viht.weathermvvm

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.viht.weathermvvm.data.local.dao.WeatherDAO
import com.viht.weathermvvm.data.remote.api.WeatherHelper
import com.viht.weathermvvm.data.remote.response.DataResponse
import com.viht.weathermvvm.data.remote.response.TemperatureResponse
import com.viht.weathermvvm.data.remote.response.WeatherDescriptionResponse
import com.viht.weathermvvm.data.remote.response.WeatherResponse
import com.viht.weathermvvm.data.repository.NetworkManager
import com.viht.weathermvvm.data.repository.WeatherRepositoryImp
import com.viht.weathermvvm.presentation.ui.main.MainViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner


@ExperimentalCoroutinesApi
//@RunWith(JUnit4::class)
@RunWith(
    MockitoJUnitRunner::class
)
class MainViewModelTest {

    private val searchKey: String = "saigon"
    private val dateSearch: String = "Mon, 23 May 2022"
    private var data: DataResponse = DataResponse(arrayListOf<WeatherResponse>().also {
        it.add(
            WeatherResponse(
                dt = 1653278400,
                humidity = 79,
                pressure = 1006,
                temp = TemperatureResponse(max = 29.97F, min = 24.62F),
                weather = arrayListOf(
                    WeatherDescriptionResponse(
                        description = "moderate rain",
                        main = "Rain"
                    )
                )
            )
        )
        it.add(
            WeatherResponse(
                dt = 1653364800,
                humidity = 55,
                pressure = 1009,
                temp = TemperatureResponse(max = 34.54F, min = 24.95F),
                weather = arrayListOf(
                    WeatherDescriptionResponse(
                        description = "overcast clouds",
                        main = "Clouds"
                    )
                )
            )
        )
        it.add(
            WeatherResponse(
                dt = 1653451200,
                humidity = 56,
                pressure = 1010,
                temp = TemperatureResponse(max = 34.26F, min = 26.12F),
                weather = arrayListOf(
                    WeatherDescriptionResponse(
                        description = "light rain",
                        main = "Rain"
                    )
                )
            )
        )
        it.add(
            WeatherResponse(
                dt = 1653537600,
                humidity = 66,
                pressure = 1008,
                temp = TemperatureResponse(max = 32.33F, min = 25.62F),
                weather = arrayListOf(
                    WeatherDescriptionResponse(
                        description = "light rain",
                        main = "Rain"
                    )
                )
            )
        )
        it.add(
            WeatherResponse(
                dt = 1653624000,
                humidity = 62,
                pressure = 1007,
                temp = TemperatureResponse(max = 31.7F, min = 25.9F),
                weather = arrayListOf(
                    WeatherDescriptionResponse(
                        description = "light rain",
                        main = "Rain"
                    )
                )
            )
        )
    })

    private val testDispatcher = StandardTestDispatcher()//TestCoroutineDispatcher()
    lateinit var mainViewModel: MainViewModel

    @Mock
    var mainRepositoryImp: WeatherRepositoryImp = mock(WeatherRepositoryImp::class.java)

    @Mock
    private var apiService: WeatherHelper = mock(WeatherHelper::class.java)

    @Mock
    lateinit var apiDao: WeatherDAO

    @Mock
    private var network: NetworkManager = mock(NetworkManager::class.java)

    @get:Rule
    val instantTaskExecutionRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

//    @Before
//    fun setup() {
//        MockitoAnnotations.initMocks(this)
//        Dispatchers.setMain(testDispatcher)
//        mainRepositoryImp = WeatherRepositoryImp(apiService, apiDao, testDispatcher, network)
//        mainViewModel = MainViewModel(mainRepositoryImp)
//    }
//
//    @Test
//    fun getLoadingTest() {
//        runBlocking {
//            mainRepositoryImp = mock(WeatherRepositoryImp::class.java)
//            `when`(mainRepositoryImp.getListForecast("saigon"))
//                .thenReturn(flowOf(ApiResult.Success(data)))
//            mainViewModel.getListForecast("saigon")
//            assertEquals(mainViewModel.loading.getOrAwaitValue(), true)
//        }
//    }

//    @Test
//    fun getWeatherBySearchKey() {
//        runBlocking {
//            mainRepository = mock(WeatherRepository::class.java)
//            `when`(mainRepository.getListForecast("saigon"))
//                .thenReturn(flowOf(ApiResult.Success(data)))
//            mainViewModel.getListForecast("saigon")
//
////            assertEquals(mainViewModel.loading.getOrAwaitValue(), true)
////            delay(5000)
//            val tmp = mainViewModel.response.value //getOrAwaitValue()
////            assertEquals(mainViewModel.loading.getOrAwaitValue(), false)
//            assertEquals(data, tmp)
//        }
//    }

//    @Test
//    fun givenServerResponse200_whenFetch_shouldReturnSuccess() {
//        mockkStatic("com.android.sample.tvmaze.util.ContextExtKt")
//        every {
//            context.isNetworkAvailable()
//        } returns true
//        `when`(api.fetchShowList()).thenReturn(Calls.response(Response.success(emptyList())))
//        `when`(dao.getShows()).thenReturn(flowOf(emptyList()))
//        val repository = ShowRepository(dao, api, context, TestContextProvider())
//        val viewModel = MainViewModel(repository).apply {
//            shows.observeForever(resource)
//        }
//        try {
//            verify(resource).onChanged(Resource.loading())
//            verify(resource).onChanged(Resource.success(emptyList()))
//        } finally {
//            viewModel.shows.removeObserver(resource)
//        }
//    }
//
//
//    @Test
//    fun `empty movie list test`() {
//        runBlocking {
//            Mockito.`when`(mainRepository.getAllMovies())
//                .thenReturn(Response.success(listOf<Movie>()))
//            mainViewModel.getAllMovies()
//            val result = mainViewModel.movieList.getOrAwaitValue()
//            assertEquals(listOf<Movie>(), result)
//        }
//    }

}