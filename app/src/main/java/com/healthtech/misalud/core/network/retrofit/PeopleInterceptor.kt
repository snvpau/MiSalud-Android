package com.healthtech.misalud.core.network.retrofit

import com.healthtech.misalud.core.network.data.clients.AuthClient
import com.healthtech.misalud.core.network.data.requests.AuthRequests
import com.healthtech.misalud.core.storage.sharedPreferences.TokenManagement
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException
import java.net.HttpURLConnection.HTTP_FORBIDDEN as FORBIDDEN
import java.net.HttpURLConnection.HTTP_OK as OK
import java.net.HttpURLConnection.HTTP_UNAUTHORIZED as UNAUTHORIZED

class PeopleInterceptor(private val retrofitHelper: RetrofitHelper): Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val authenticationRequest = request(originalRequest)
        val initialResponse = chain.proceed(authenticationRequest)

        if(initialResponse.code() == UNAUTHORIZED || initialResponse.code() == FORBIDDEN) {
            initialResponse.close()
            val responseNewToken = TokenManagement.refreshToken?.let {
                retrofitHelper.getRetrofitAuth()
                    .create(AuthClient::class.java)
                    .doRefreshAccessToken(AuthRequests.PostRefreshAccessToken(it)).execute()
            }

            if (responseNewToken != null) {
                if(responseNewToken.code() == OK) {
                    responseNewToken.body()?.let {
                        TokenManagement.accessToken = it.accessToken

                    }
                }
            }
        } else{
            return initialResponse
        }
        val newAuthenticationRequest = request(originalRequest)
        return chain.proceed(newAuthenticationRequest)
    }

    private fun request(originalRequest: Request): Request {
        return originalRequest.newBuilder()
            .addHeader("Authorization", "Bearer ")
            .addHeader("Accept", "application/json")
            .build()

    }

}