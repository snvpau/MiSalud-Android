package com.healthtech.misalud.core.network.data.clients

import com.healthtech.misalud.core.network.data.requests.LogoutRequest
import com.healthtech.misalud.core.network.data.responses.LogoutResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LogoutClient {
    @POST("/api/v1/auth/logout")
    suspend fun doLogout(@Body logoutRequest: LogoutRequest) : Response<LogoutResponse>
}