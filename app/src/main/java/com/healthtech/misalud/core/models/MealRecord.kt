package com.healthtech.misalud.core.models

data class MealRecord (
    val id: Int,
    val uuid: String,
    val name: String,
    val type: String,
    val score: Float,
    val createdAt: String,
    val updatedAt: String,
)