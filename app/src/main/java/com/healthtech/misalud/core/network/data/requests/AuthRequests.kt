package com.healthtech.misalud.core.network.data.requests

class AuthRequests{
    data class PostLogin (
        val phoneNumber: String,
        val password: String,
    )

    data class PostLogout (
        val refreshToken: String
    )

    data class PostRegistry (
        val firstName: String,
        val lastName: String,
        val phoneNumber: String,
        val password: String,
    )

    data class PostRefreshAccessToken (
        val refreshToken: String
    )
}