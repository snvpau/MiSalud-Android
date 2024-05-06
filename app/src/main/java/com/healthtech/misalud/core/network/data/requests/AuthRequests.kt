package com.healthtech.misalud.core.network.data.requests

class AuthRequests{
    data class LoginRequest(
        val phoneNumber: String,
        val password: String,
    )

    data class LogoutRequest (
        val refreshToken: String
    )

    data class RegistryRequest (
        val firstName: String,
        val lastName: String,
        val phoneNumber: String,
        val password: String,
    )
}