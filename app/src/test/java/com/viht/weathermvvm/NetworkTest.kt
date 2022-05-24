package com.viht.weathermvvm

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.viht.weathermvvm.data.repository.ApiResponse
import com.viht.weathermvvm.data.repository.ApiResult
import com.viht.weathermvvm.data.repository.NetworkManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response

@ExperimentalCoroutinesApi
//@RunWith(JUnit4::class)
@RunWith(
    MockitoJUnitRunner::class
)
class NetworkTest {
    private val testDispatcher = StandardTestDispatcher()


    @Mock
    private var network: NetworkManager = mock(NetworkManager::class.java)

    @get:Rule
    val instantTaskExecutionRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(testDispatcher)
    }

    @Test
    fun `when lambda returns successfully then it should emit the result as success`() {
        runBlocking {
            network = mock(NetworkManager::class.java)
            val lambdaResult : Response<Boolean> = Response.success(true)
            val result = ApiResponse().safeApiCall { Response.success(lambdaResult) }
            assertEquals(ApiResult.Success(lambdaResult), result)
        }
    }

//    @Test
//    fun whenMockFinalClassMockWorks() {
//        val finalList = FinalList()
//        val mock: FinalList = mock(FinalList::class.java)
//        `when`(mock.size()).thenReturn(2)
//        assertNotEquals(mock.size(), finalList.size())
//    }

//    @Test
//    fun `when lambda throws IOException then it should emit the result as NetworkError`() {
//        runBlockingTest {
//            val result = ApiResponse.safeApiCall { () -> throw IOException() }
//            assertEquals(ApiResult.Error(), result)
//        }
//    }

//    @Test
//    fun `when lambda throws HttpException then it should emit the result as GenericError`() {
//        val errorBody = "{\"errors\": [\"Unexpected parameter\"]}".toResponseBody("application/json".toMediaTypeOrNull())
//
//        runBlockingTest {
//            val result = safeApiCall(dispatcher) {
//                throw HttpException(Response.error<Any>(422, errorBody))
//            }
//            assertEquals(ApiResult.GenericError(422, ErrorResponse(listOf("Unexpected parameter"))), result)
//        }
//    }
//
//    @Test
//    fun `when lambda throws unknown exception then it should emit GenericError`() {
//        runBlockingTest {
//            val result = safeApiCall(dispatcher) {
//                throw IllegalStateException()
//            }
//            assertEquals(ApiResult.GenericError(), result)
//        }
//    }
}