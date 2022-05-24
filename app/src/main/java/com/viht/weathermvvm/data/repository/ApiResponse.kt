package com.viht.weathermvvm.data.repository

import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import retrofit2.Response
import javax.inject.Inject

open class ApiResponse @Inject constructor(
) {
    @Inject
    lateinit var networkManager: NetworkManager

    suspend fun <T> safeApiCall(apiCall: suspend () -> Response<T>): ApiResult<T> {
        if (networkManager.isNetworkAvailable()) {
            try {
                val response = apiCall()
                if (response.isSuccessful) {
                    val body = response.body()
                    body?.let {
                        return ApiResult.Success(body)
                    }
                }
                val gson = Gson()
                val type = object : TypeToken<Failure>() {}.type
                val errorResponse: Failure? = gson.fromJson(response.errorBody()?.string(), type)
                Log.d("viht","Api call failed: ${errorResponse?.message}")
                return error(errorResponse?.message ?: "")
            } catch (e: Exception) {
                return error("Exception failed: ${e.message ?: e.toString()}")
            }
        } else {
            return error("No internet connection")
        }
    }

    private fun <T> error(errorMessage: String): ApiResult<T> =
        ApiResult.Error("$errorMessage")
}