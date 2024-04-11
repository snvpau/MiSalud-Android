package com.healthtech.misalud.core.network.data.services

import com.google.gson.Gson
import com.healthtech.misalud.core.network.retrofit.RetrofitHelper
import com.healthtech.misalud.core.network.data.clients.LoginClient
import com.healthtech.misalud.core.network.data.requests.LoginRequest
import com.healthtech.misalud.core.network.data.responses.LoginResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject

class LoginService {
    private val retrofit = RetrofitHelper.getRetrofitAuth()

    suspend fun doLogin(phoneNumber: String, password: String) : LoginResponse {
        val loginRequest = LoginRequest(phoneNumber, password)

        return withContext(Dispatchers.IO){
            val response = retrofit.create(LoginClient::class.java).doLogin(loginRequest)

            if(!response.isSuccessful) {
                val jsonObject = JSONObject(response.errorBody()!!.charStream().readText()).toString()
                return@withContext Gson().fromJson<LoginResponse?>(jsonObject, LoginResponse::class.java)
            }

            return@withContext response.body()!!
        }
    }
}