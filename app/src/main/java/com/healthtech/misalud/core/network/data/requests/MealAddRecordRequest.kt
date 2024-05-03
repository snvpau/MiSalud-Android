package com.healthtech.misalud.core.network.data.requests

data class MealAddRecordRequest(
    val userId: String,
    val name: String,
    val type: String,
    val score: Int
)