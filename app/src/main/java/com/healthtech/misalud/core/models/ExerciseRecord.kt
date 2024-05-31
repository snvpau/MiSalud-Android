package com.healthtech.misalud.core.models

data class ExerciseRecord (
    val id: Int,
    val uuid: String,
    val name: String,
    val duration: Int, //Duracion min
    val score: Float,
    val createdAt: String,
    val updatedAt: String
)