package com.healthtech.misalud.core.network.data.responses

import com.healthtech.misalud.core.models.RequestError

data class AuthResponse (
    val error: RequestError?,
    val success: Boolean?,
    val accessToken: String?,
    val refreshToken: String?
)