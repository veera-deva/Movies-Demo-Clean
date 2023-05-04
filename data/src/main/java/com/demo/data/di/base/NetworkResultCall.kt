package com.demo.data.di.base

import com.demo.domain.model.NetworkResult
import okhttp3.Request
import okio.Timeout
import retrofit2.Callback
import retrofit2.Response

class NetworkResultCall<T : Any>(private val proxy: retrofit2.Call<T>) :
    retrofit2.Call<NetworkResult<T>> {
    override fun clone(): retrofit2.Call<NetworkResult<T>> {
        TODO("Not yet implemented")
    }

    override fun execute(): Response<NetworkResult<T>> {
        TODO("Not yet implemented")
    }

    override fun isExecuted(): Boolean {
        TODO("Not yet implemented")
    }

    override fun cancel() {
        TODO("Not yet implemented")
    }

    override fun isCanceled(): Boolean {
        TODO("Not yet implemented")
    }

    override fun request(): Request {
        TODO("Not yet implemented")
    }

    override fun timeout(): Timeout {
        TODO("Not yet implemented")
    }

    override fun enqueue(callback: Callback<NetworkResult<T>>) {
    }

}