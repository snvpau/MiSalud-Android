package com.healthtech.misalud.core.network.data.services

import com.google.gson.Gson
import com.healthtech.misalud.core.network.data.clients.RegistryClient
import com.healthtech.misalud.core.network.data.requests.RegistryRequest
import com.healthtech.misalud.core.network.data.responses.AuthResponse
import com.healthtech.misalud.core.network.retrofit.RetrofitHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject

class RegistryService {
    private val retrofit = RetrofitHelper.getRetrofitAuth()

    suspend fun doUserRegistry(firstName: String, lastName: String, phoneNumber: String, password: String) : AuthResponse {
        val registryRequest = RegistryRequest(firstName, lastName, phoneNumber, password)

        return withContext(Dispatchers.IO){
            val response = retrofit.create(RegistryClient::class.java).doRegisterUser(registryRequest)

            if(!response.isSuccessful) {
                val jsonObject = JSONObject(response.errorBody()!!.charStream().readText()).toString()
                return@withContext Gson().fromJson<AuthResponse?>(jsonObject, AuthResponse::class.java)
            }

            return@withContext response.body()!!
        }
    }
}