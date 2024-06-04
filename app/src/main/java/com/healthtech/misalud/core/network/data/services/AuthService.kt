package com.healthtech.misalud.core.network.data.services

import com.google.gson.Gson
import com.healthtech.misalud.core.network.retrofit.RetrofitHelper
import com.healthtech.misalud.core.network.data.clients.AuthClient
import com.healthtech.misalud.core.network.data.requests.AuthRequests
import com.healthtech.misalud.core.network.data.responses.AuthResponses
import retrofit2.Response
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject

class AuthService {
    private val retrofit = RetrofitHelper.getRetrofitAuth()

    suspend fun doLogin(phoneNumber: String, password: String) : AuthResponses.PostLogin {
        val loginRequest = AuthRequests.PostLogin(phoneNumber, password)

        return withContext(Dispatchers.IO){
            val response = retrofit.create(AuthClient::class.java).doLogin(loginRequest)

            if(!response.isSuccessful) {
                val jsonObject = JSONObject(response.errorBody()!!.charStream().readText()).toString()
                return@withContext Gson().fromJson<AuthResponses.PostLogin?>(jsonObject, AuthResponses.PostLogin::class.java)
            }

            return@withContext response.body()!!
        }
    }

    suspend fun doLogout(refreshToken: String) : AuthResponses.PostLogout {
        val logoutRequest = AuthRequests.PostLogout(refreshToken)

        return withContext(Dispatchers.IO){
            val response = retrofit.create(AuthClient::class.java).doLogout(logoutRequest)

            if(!response.isSuccessful) {
                val jsonObject = JSONObject(response.errorBody()!!.charStream().readText()).toString()
                return@withContext Gson().fromJson<AuthResponses.PostLogout?>(jsonObject, AuthResponses.PostLogout::class.java)
            }

            return@withContext response.body()!!
        }
    }

    suspend fun doCreateAccount(firstName: String, lastName: String, phoneNumber: String, password: String) : AuthResponses.PostLogin {
        val registryRequest = AuthRequests.PostRegistry(firstName, lastName, phoneNumber, password)

        return withContext(Dispatchers.IO){
            val response = retrofit.create(AuthClient::class.java).doRegisterUser(registryRequest)

            if(!response.isSuccessful) {
                val jsonObject = JSONObject(response.errorBody()!!.charStream().readText()).toString()
                return@withContext Gson().fromJson<AuthResponses.PostLogin?>(jsonObject, AuthResponses.PostLogin::class.java)
            }

            return@withContext response.body()!!
        }
    }

    object RetrofitInstance {
        val api: AuthClient by lazy {
            RetrofitHelper.getRetrofitAuth().create(AuthClient::class.java)
        }
    }
    suspend fun changePassword(uuid: String, currentPassword: String, newPassword: String): Response<AuthResponses.PostChangePassword> {
        val request = AuthRequests.PostChangePassword(uuid, currentPassword, newPassword)
        return RetrofitInstance.api.doChangePassword(request)
    }

}