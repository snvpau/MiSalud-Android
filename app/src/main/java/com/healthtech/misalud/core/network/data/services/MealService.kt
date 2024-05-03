package com.healthtech.misalud.core.network.data.services

import com.google.gson.Gson
import com.healthtech.misalud.core.network.data.clients.MealAddRecordClient
import com.healthtech.misalud.core.network.data.requests.MealAddRecordRequest
import com.healthtech.misalud.core.network.data.responses.MealAddRecordResponse
import com.healthtech.misalud.core.network.retrofit.RetrofitHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject

class MealService {
    private val retrofit = RetrofitHelper.getRetrofitPeople()

    suspend fun doAddRecord(userId: String, name: String, type: String, score: Int) : MealAddRecordResponse {
        val mealRequest = MealAddRecordRequest(userId, name, type, score)

        return withContext(Dispatchers.IO){
            val response = retrofit.create(MealAddRecordClient::class.java).doAddRecord(mealRequest)

            if(!response.isSuccessful) {
                val jsonObject = JSONObject(response.errorBody()!!.charStream().readText()).toString()
                return@withContext Gson().fromJson<MealAddRecordResponse?>(jsonObject, MealAddRecordResponse::class.java)
            }

            return@withContext response.body()!!
        }
    }

}