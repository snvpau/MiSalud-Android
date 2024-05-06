package com.healthtech.misalud.core.network.data.clients

import com.healthtech.misalud.core.network.data.requests.AuthRequests
import com.healthtech.misalud.core.network.data.responses.AuthResponses
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthClient {
    @POST("/api/v1/auth/login")
    suspend fun doLogin(@Body loginRequest: AuthRequests.LoginRequest) : Response<AuthResponses.LoginResponse>

    @POST("/api/v1/auth/logout")
    suspend fun doLogout(@Body logoutRequest: AuthRequests.LogoutRequest) : Response<AuthResponses.LogoutResponse>

    @POST("/api/v1/auth/register")
    suspend fun doRegisterUser(@Body registryRequest: AuthRequests.RegistryRequest) : Response<AuthResponses.LoginResponse>
}

