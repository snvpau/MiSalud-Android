package com.healthtech.misalud.core.network.data.responses

import com.healthtech.misalud.core.models.RequestError

class AuthResponses {
    data class PostLogin (
        val error: RequestError?,
        val success: Boolean?,
        val accessToken: String?,
        val refreshToken: String?,
        val uuid: String?
    )

    data class PostLogout (
        val error: RequestError?,
        val success: Boolean?
    )

    data class PostRefreshAccessToken (
        val error: RequestError?,
        val success: Boolean?,
        val accessToken: String?
    )
}