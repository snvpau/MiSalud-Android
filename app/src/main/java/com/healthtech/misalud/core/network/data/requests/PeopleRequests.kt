package com.healthtech.misalud.core.network.data.requests

class PeopleRequests {
    data class PostMealRecord (
        val uuid: String,
        val name: String,
        val type: String,
        val score: Float
    )
    data class PostExerciseRecord (
        val uuid: String,
        val name: String,
        val duration: Int,
        val score: Float
    )
}