package com.healthtech.misalud.core.network.retrofit

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
    fun getRetrofitAuth() : Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://ms-auth-gez4.onrender.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getRetrofitPeople() : Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://ms-people.onrender.com")
            .client(createPeopleAuth())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun createPeopleAuth(): OkHttpClient {
        val okHttpClientBuilder: OkHttpClient.Builder = OkHttpClient.Builder()

        okHttpClientBuilder.addInterceptor(PeopleInterceptor(this))
        return okHttpClientBuilder.build()
    }
}