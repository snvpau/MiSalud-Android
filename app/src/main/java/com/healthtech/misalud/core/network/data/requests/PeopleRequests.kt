package com.healthtech.misalud.core.network.data.requests

class PeopleRequests {
    data class HomeRequest (
        val phoneNumber: String
    )

    data class MealAddRecordRequest(
        val userId: String,
        val name: String,
        val type: String,
        val score: Int
    )
}