package com.healthtech.misalud.core.network.data.services

import com.google.gson.Gson
import com.healthtech.misalud.core.network.retrofit.RetrofitHelper
import com.healthtech.misalud.core.network.data.clients.LoginClient
import com.healthtech.misalud.core.network.data.clients.LogoutClient
import com.healthtech.misalud.core.network.data.requests.LoginRequest
import com.healthtech.misalud.core.network.data.requests.LogoutRequest
import com.healthtech.misalud.core.network.data.responses.LoginResponse
import com.healthtech.misalud.core.network.data.responses.LogoutResponse
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

    suspend fun doLogout(refreshToken: String) : LogoutResponse {
        val logoutRequest = LogoutRequest(refreshToken)

        return withContext(Dispatchers.IO){
            val response = retrofit.create(LogoutClient::class.java).doLogout(logoutRequest)

            if(!response.isSuccessful) {
                val jsonObject = JSONObject(response.errorBody()!!.charStream().readText()).toString()
                return@withContext Gson().fromJson<LogoutResponse?>(jsonObject, LogoutResponse::class.java)
            }

            return@withContext response.body()!!
        }
    }
}