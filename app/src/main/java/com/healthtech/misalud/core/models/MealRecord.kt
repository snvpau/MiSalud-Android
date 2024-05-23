package com.healthtech.misalud.core.models

data class MealRecord (
    val id: Int,
    val uuid: String,
    val name: String,
    val type: String,
    val createdAt: String,
    val updatedAt: String,
)