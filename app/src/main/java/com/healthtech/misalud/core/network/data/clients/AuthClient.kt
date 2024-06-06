package com.healthtech.misalud.core.network.data.clients

import com.healthtech.misalud.core.network.data.requests.AuthRequests
import com.healthtech.misalud.core.network.data.responses.AuthResponses
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthClient {
    @POST("/api/v1/auth/login")
    suspend fun doLogin(@Body loginRequest: AuthRequests.PostLogin) : Response<AuthResponses.PostLogin>

    @POST("/api/v1/auth/logout")
    suspend fun doLogout(@Body logoutRequest: AuthRequests.PostLogout) : Response<AuthResponses.PostLogout>

    @POST("/api/v1/auth/register")
    suspend fun doRegisterUser(@Body registryRequest: AuthRequests.PostRegistry) : Response<AuthResponses.PostLogin>

    @POST("/api/v1/auth/refreshAccessToken")
    fun doRefreshAccessToken(@Body refreshTokenRequest: AuthRequests.PostRefreshAccessToken) : Call<AuthResponses.PostRefreshAccessToken>

    @POST("/api/v1/auth/changePassword")
    suspend fun doChangePassword(@Body changePasswordRequest: AuthRequests.PostChangePassword): Response<AuthResponses.PostChangePassword>

}

