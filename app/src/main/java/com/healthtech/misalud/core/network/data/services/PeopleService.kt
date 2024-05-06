package com.healthtech.misalud.core.network.data.services

import com.google.gson.Gson
import com.healthtech.misalud.core.network.data.clients.PeopleClient
import com.healthtech.misalud.core.network.data.requests.PeopleRequests
import com.healthtech.misalud.core.network.data.responses.PeopleResponses
import com.healthtech.misalud.core.network.retrofit.RetrofitHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject

class PeopleService {
    private val retrofit = RetrofitHelper.getRetrofitPeople()

    suspend fun doGetUser(phoneNumber: String, accessToken: String) : PeopleResponses.HomeResponse {
        val homeRequest = PeopleRequests.HomeRequest(phoneNumber)

        return withContext(Dispatchers.IO){
            val response = retrofit.create(PeopleClient::class.java).doGetUser(accessToken = accessToken, phoneNumber = homeRequest.phoneNumber)

            if(!response.isSuccessful) {
                val jsonObject = JSONObject(response.errorBody()!!.charStream().readText()).toString()
                return@withContext Gson().fromJson<PeopleResponses.HomeResponse?>(jsonObject, PeopleResponses.HomeResponse::class.java)
            }

            return@withContext response.body()!!
        }
    }

    suspend fun doAddRecord(accessToken: String, userId: String, name: String, type: String, score: Int) : PeopleResponses.MealAddRecordResponse {
        val mealRequest = PeopleRequests.MealAddRecordRequest(userId, name, type, score)

        return withContext(Dispatchers.IO){
            val response = retrofit.create(PeopleClient::class.java).doAddRecord(accessToken, mealRequest)

            if(!response.isSuccessful) {
                val jsonObject = JSONObject(response.errorBody()!!.charStream().readText()).toString()
                return@withContext Gson().fromJson<PeopleResponses.MealAddRecordResponse?>(jsonObject, PeopleResponses.MealAddRecordResponse::class.java)
            }

            return@withContext response.body()!!
        }
    }
}