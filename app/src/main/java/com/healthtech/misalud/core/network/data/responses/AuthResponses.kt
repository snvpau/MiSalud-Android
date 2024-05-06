package com.healthtech.misalud.core.network.data.responses

import com.healthtech.misalud.core.models.RequestError

class AuthResponses {
    data class LoginResponse(
        val error: RequestError?,
        val success: Boolean?,
        val accessToken: String?,
        val refreshToken: String?
    )

    data class LogoutResponse (
        val error: RequestError?,
        val success: Boolean?
    )
}