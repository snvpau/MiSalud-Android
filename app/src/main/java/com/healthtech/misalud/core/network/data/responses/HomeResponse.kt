package com.healthtech.misalud.core.network.data.responses

import com.healthtech.misalud.core.models.UserModel

data class HomeResponse(
    val success: Boolean,
    val user: UserModel
)