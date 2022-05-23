package com.viht.weathermvvm

import com.viht.weathermvvm.repository.ApiResult
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Test

class NetworkTest {
//    private val dispatcher = StandardTestDispatcher()
//
//    @Test
//    fun `when lambda returns successfully then it should emit the result as success`() {
//        runTest {
//            val lambdaResult = true
//            val result = safeApiCall(dispatcher) { lambdaResult }
//            assertEquals(ApiResult.Success(lambdaResult), result)
//        }
//    }
//
//    @Test
//    fun `when lambda throws IOException then it should emit the result as NetworkError`() {
//        runBlockingTest {
//            val result = safeApiCall(dispatcher) { throw IOException() }
//            assertEquals(ApiResult.NetworkError, result)
//        }
//    }
//
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