package com.healthtech.misalud.core.network.data.responses

data class RequestError(
    val status: Int,
    val message: String
)

data class LoginResponse(
    val error: RequestError?,
    val success: Boolean?,
    val accessToken: String?,
    val refreshToken: String?
)