package com.healthtech.misalud.core.network.data.responses

import com.healthtech.misalud.core.models.RequestError
import com.healthtech.misalud.core.models.UserModel

class PeopleResponses {
    data class HomeResponse(
        val success: Boolean,
        val user: UserModel
    )

    data class MealAddRecordResponse(
        val error: RequestError?,
        val success: Boolean?
    )
}