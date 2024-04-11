package com.healthtech.misalud.core.network.data.clients

import com.healthtech.misalud.core.network.data.responses.HomeResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface HomeClient {
    @GET("/api/v1/people/user")
    suspend fun doGetUser(@Header("Authorization") accessToken: String, @Query("phoneNumber") phoneNumber: String) : Response<HomeResponse>
}