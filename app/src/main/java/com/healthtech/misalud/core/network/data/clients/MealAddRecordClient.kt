package com.healthtech.misalud.core.network.data.clients

import com.healthtech.misalud.core.network.data.requests.MealAddRecordRequest
import com.healthtech.misalud.core.network.data.responses.MealAddRecordResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface MealAddRecordClient {
    @POST("/api/v1/meals/records")
    suspend fun doAddRecord(@Body addRecordRequest: MealAddRecordRequest) : Response<MealAddRecordResponse>
}