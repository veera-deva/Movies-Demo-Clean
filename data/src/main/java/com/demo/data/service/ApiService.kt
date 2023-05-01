package com.demo.data.di.service

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("v4/articles/")
    suspend fun getEvents(@Query("limit") limit: Int)

    @GET("v4/articles/{eventId}")
    suspend fun getEventDetails(@Path(value = "eventId") eventId: Long)

}