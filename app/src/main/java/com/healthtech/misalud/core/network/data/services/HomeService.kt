package com.healthtech.misalud.core.network.data.services

import com.google.gson.Gson
import com.healthtech.misalud.core.network.data.clients.HomeClient
import com.healthtech.misalud.core.network.data.requests.HomeRequest
import com.healthtech.misalud.core.network.data.responses.HomeResponse
import com.healthtech.misalud.core.network.retrofit.RetrofitHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject

class HomeService {
    private val retrofit = RetrofitHelper.getRetrofitPeople()

    suspend fun doGetUser(phoneNumber: String, accessToken: String) : HomeResponse {
        val homeRequest = HomeRequest(phoneNumber)

        return withContext(Dispatchers.IO){
            val response = retrofit.create(HomeClient::class.java).doGetUser(accessToken = accessToken, phoneNumber = homeRequest.phoneNumber)

            if(!response.isSuccessful) {
                val jsonObject = JSONObject(response.errorBody()!!.charStream().readText()).toString()
                return@withContext Gson().fromJson<HomeResponse?>(jsonObject, HomeResponse::class.java)
            }

            return@withContext response.body()!!
        }
    }
}