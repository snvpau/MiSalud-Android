package com.healthtech.misalud.core.network.data.services

import com.google.gson.Gson
import com.healthtech.misalud.core.network.retrofit.RetrofitHelper
import com.healthtech.misalud.core.network.data.clients.AuthClient
import com.healthtech.misalud.core.network.data.requests.AuthRequests
import com.healthtech.misalud.core.network.data.responses.AuthResponses
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject

class AuthService {
    private val retrofit = RetrofitHelper.getRetrofitAuth()

    suspend fun doLogin(phoneNumber: String, password: String) : AuthResponses.LoginResponse {
        val loginRequest = AuthRequests.LoginRequest(phoneNumber, password)

        return withContext(Dispatchers.IO){
            val response = retrofit.create(AuthClient::class.java).doLogin(loginRequest)

            if(!response.isSuccessful) {
                val jsonObject = JSONObject(response.errorBody()!!.charStream().readText()).toString()
                return@withContext Gson().fromJson<AuthResponses.LoginResponse?>(jsonObject, AuthResponses.LoginResponse::class.java)
            }

            return@withContext response.body()!!
        }
    }

    suspend fun doLogout(refreshToken: String) : AuthResponses.LogoutResponse {
        val logoutRequest = AuthRequests.LogoutRequest(refreshToken)

        return withContext(Dispatchers.IO){
            val response = retrofit.create(AuthClient::class.java).doLogout(logoutRequest)

            if(!response.isSuccessful) {
                val jsonObject = JSONObject(response.errorBody()!!.charStream().readText()).toString()
                return@withContext Gson().fromJson<AuthResponses.LogoutResponse?>(jsonObject, AuthResponses.LogoutResponse::class.java)
            }

            return@withContext response.body()!!
        }
    }

    suspend fun doCreateAccount(firstName: String, lastName: String, phoneNumber: String, password: String) : AuthResponses.LoginResponse {
        val registryRequest = AuthRequests.RegistryRequest(firstName, lastName, phoneNumber, password)

        return withContext(Dispatchers.IO){
            val response = retrofit.create(AuthClient::class.java).doRegisterUser(registryRequest)

            if(!response.isSuccessful) {
                val jsonObject = JSONObject(response.errorBody()!!.charStream().readText()).toString()
                return@withContext Gson().fromJson<AuthResponses.LoginResponse?>(jsonObject, AuthResponses.LoginResponse::class.java)
            }

            return@withContext response.body()!!
        }
    }
}