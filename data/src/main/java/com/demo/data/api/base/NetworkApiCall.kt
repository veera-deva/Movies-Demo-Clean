package com.demo.data.api.base

import com.demo.domain.model.NetworkResult
import retrofit2.HttpException
import retrofit2.Response

object NetworkApiCall {

    suspend fun <T : Any> handleApi(execute: suspend () -> Response<T>): NetworkResult<T> {
        return try {
            val response = execute()
            val body = response.body()
            if (response.isSuccessful && body != null) {
                NetworkResult.Success(body)
            } else {
                NetworkResult.Error(code = response.code(), message = response.message())
            }
        } catch (e: HttpException) {
            NetworkResult.Error(code = e.code(), message = e.message())
        } catch (e: Throwable) {
            NetworkResult.Failure(e)
        }
    }
}