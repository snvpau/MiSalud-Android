package com.healthtech.misalud.core.network.data.responses

import com.healthtech.misalud.core.models.RequestError

data class LogoutResponse (
    val error: RequestError?,
    val success: Boolean?,
)