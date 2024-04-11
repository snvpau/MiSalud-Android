package com.healthtech.misalud.core.network.data.clients

import com.healthtech.misalud.core.network.data.requests.LoginRequest
import com.healthtech.misalud.core.network.data.responses.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginClient {
    @POST("/api/v1/auth/login")
    suspend fun doLogin(@Body loginRequest: LoginRequest) : Response<LoginResponse>
}