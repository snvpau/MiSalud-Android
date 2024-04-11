package com.healthtech.misalud.core.models

data class UserModel(
    val id: Int,
    val uuid: String,
    val firstName: String,
    val lastName: String,
    val phoneNumber: String,
    val role: String,
    val createdAt: String,
)