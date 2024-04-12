package com.healthtech.misalud.core.network.data.clients

import com.healthtech.misalud.core.network.data.requests.RegistryRequest
import com.healthtech.misalud.core.network.data.responses.AuthResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface RegistryClient {
    @POST("/api/v1/auth/register")
    suspend fun doRegisterUser(@Body registryRequest: RegistryRequest) : Response<AuthResponse>
}