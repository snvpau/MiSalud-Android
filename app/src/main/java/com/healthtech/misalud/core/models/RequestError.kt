package com.healthtech.misalud.core.models

data class RequestError(
    val status: Int,
    val message: String
)