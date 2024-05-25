package com.healthtech.misalud.core.models

data class ExerciseRecord (
    val id: Int,
    val uuid: String,
    val name: String,
    val type: String,
    val createdAt: String,
    val updatedAt: String,
    val duration: Int, //Duracion min
    val satisfaction: Int
)