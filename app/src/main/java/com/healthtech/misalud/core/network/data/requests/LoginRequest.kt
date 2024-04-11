package com.healthtech.misalud.core.network.data.requests

data class LoginRequest(
    val phoneNumber: String,
    val password: String,
)